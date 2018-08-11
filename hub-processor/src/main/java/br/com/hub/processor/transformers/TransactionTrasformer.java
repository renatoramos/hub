package br.com.hub.processor.transformers;

import br.com.hub.core.transformers.TransactionService;
import br.com.hub.domain.Transaction;
import br.com.hub.domain.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Service;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
@Service
public class TransactionTrasformer implements GenericTransformer<Transaction, TransactionResponse> {

    @Autowired
    private TransactionService service;

    @Override
    public TransactionResponse transform(Transaction transaction) {
        return service.apply(transaction);
    }
}
