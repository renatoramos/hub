package br.com.hub.domain.builders;

import br.com.hub.domain.Transaction;
import br.com.hub.domain.types.TransactionType;

/**
 * Created by STELO\renato.5a on 8/11/18.
 */
public final class TransactionBuilder {
    private TransactionType action;
    private String cardnumber;
    private Double amount;

    private TransactionBuilder() {
    }

    public static TransactionBuilder aTransaction() {
        return new TransactionBuilder();
    }

    public TransactionBuilder withAction(TransactionType action) {
        this.action = action;
        return this;
    }

    public TransactionBuilder withCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
        return this;
    }

    public TransactionBuilder withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setAction(action == null ? null : action.code());
        transaction.setCardnumber(cardnumber);
        transaction.setAmount(amount);
        return transaction;
    }
}
