package br.com.hub.domain.types;

/**
 * Created by STELO\renato.5a on 8/11/18.
 */
public enum TransactionType {
    WITHDRAW("withdraw"),
    BALANCE("balance"),
    DEPOSIT("deposit");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }

}
