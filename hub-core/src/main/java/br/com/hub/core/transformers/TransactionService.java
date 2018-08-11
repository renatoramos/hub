package br.com.hub.core.transformers;

import br.com.hub.domain.*;
import br.com.hub.domain.builders.CardBuilder;
import br.com.hub.domain.builders.TransactionAuthorizationBuilder;
import br.com.hub.domain.builders.TransactionHistoryBuilder;
import br.com.hub.core.repositories.CardRepository;
import br.com.hub.domain.types.AuthorizationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.hub.core.validators.CardValidator.validateTrancaton;
import static br.com.hub.domain.types.TransactionType.BALANCE;
import static br.com.hub.domain.types.TransactionType.DEPOSIT;
import static br.com.hub.domain.types.TransactionType.WITHDRAW;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
@Service
public class TransactionService {

    @Autowired
    private CardRepository repository;

    private Card balance(String cardNumber) {
        return repository.findById(cardNumber).get();
    }

    private TransactionAuthorization deposit(Transaction transaction) {
        Card card = repository.findById(transaction.getCardnumber()).orElse(CardBuilder.anCard()
                .withCardnumber(transaction.getCardnumber())
                .withAvailableAmount(0D).build());
        card.setAvailableAmount(card.getAvailableAmount() + transaction.getAmount());
        repository.save(card);
        return TransactionAuthorizationBuilder
                .aTransactionAuthorization()
                .withAuthorization_code(AuthorizationType.AUTHORIZED.code())
                .withAction(transaction.getAction())
                .build();
    }

    private TransactionAuthorization withdraw(Transaction transaction) {
        return repository.findById(transaction.getCardnumber()).map(card -> {
                    double value = card.getAvailableAmount();
                    if (value >= transaction.getAmount()) {
                        TransactionAuthorization authorization = TransactionAuthorizationBuilder.aTransactionAuthorization()
                                .withAuthorization_code(AuthorizationType.AUTHORIZED.code())
                                .withAction(transaction.getAction())
                                .build();

                        card.setAvailableAmount(value - transaction.getAmount());
                        card.addTransaction(TransactionHistoryBuilder
                                .aTransactionHistory()
                                .withAuthorizationCode(authorization.getCode())
                                .withAmout(transaction.getAmount()).build());
                        repository.save(card);

                        return authorization;
                    } else {
                        return TransactionAuthorizationBuilder.aTransactionAuthorization()
                                .withAuthorization_code(AuthorizationType.INSULFFICIENT_BALANCE.code())
                                .withAction(transaction.getAction())
                                .build();
                    }
                }
        ).orElse(TransactionAuthorizationBuilder.aTransactionAuthorization()
                .withAuthorization_code(AuthorizationType.INVALID_ACCOUNT.code())
                .withAction(transaction.getAction())
                .build());
    }

    public TransactionResponse apply(Transaction transaction) {
        try {
            validateTrancaton(transaction);
            if (WITHDRAW.code().equals(transaction.getAction())) {
                return this.withdraw(transaction);
            }
            if (DEPOSIT.code().equals(transaction.getAction())) {
                return this.deposit(transaction);
            }
            if (BALANCE.code().equals(transaction.getAction())) {
                return this.balance(transaction.getCardnumber());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return TransactionAuthorizationBuilder
                .aTransactionAuthorization()
                .withAuthorization_code(AuthorizationType.PROCESS_ERROR.code())
                .withAction(transaction.getAction())
                .build();
    }
}
