package br.com.hub.domain.builders;

import br.com.hub.domain.TransactionAuthorization;

import java.util.Random;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
public final class TransactionAuthorizationBuilder {
    private String action;
    private String authorization_code;

    private TransactionAuthorizationBuilder() {
    }

    public static TransactionAuthorizationBuilder aTransactionAuthorization() {
        return new TransactionAuthorizationBuilder();
    }

    public TransactionAuthorizationBuilder withAction(String action) {
        this.action = action;
        return this;
    }

    public TransactionAuthorizationBuilder withAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
        return this;
    }

    public TransactionAuthorization build() {
        TransactionAuthorization transactionAuthorization = new TransactionAuthorization();
        transactionAuthorization.setAction(action);
        transactionAuthorization.setCode(generateAuthorizationCode());
        transactionAuthorization.setAuthorization_code(authorization_code);
        return transactionAuthorization;
    }

    private String generateAuthorizationCode() {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());
        Integer authorizationCode = generator.nextInt(1000000) % 1000000;
        java.text.DecimalFormat f = new java.text.DecimalFormat("000000");
        return f.format(authorizationCode);
    }
}
