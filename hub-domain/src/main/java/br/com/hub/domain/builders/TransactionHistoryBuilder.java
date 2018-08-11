package br.com.hub.domain.builders;

import br.com.hub.domain.TransactionHistory;

import java.time.LocalDateTime;

/**
 * Created by STELO\renato.5a on 8/11/18.
 */
public final class TransactionHistoryBuilder {
    private Double amout;
    private String authorizationCode;

    private TransactionHistoryBuilder() {
    }

    public static TransactionHistoryBuilder aTransactionHistory() {
        return new TransactionHistoryBuilder();
    }

    public TransactionHistoryBuilder withAmout(Double amout) {
        this.amout = amout;
        return this;
    }

    public TransactionHistoryBuilder withAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
        return this;
    }

    public TransactionHistory build() {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setDate(LocalDateTime.now());
        transactionHistory.setAmout(amout);
        transactionHistory.setAuthorizationCode(authorizationCode);
        return transactionHistory;
    }
}
