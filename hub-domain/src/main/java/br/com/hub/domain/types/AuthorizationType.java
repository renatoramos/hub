package br.com.hub.domain.types;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
public enum AuthorizationType {
    AUTHORIZED("00"),
    INSULFFICIENT_BALANCE("51"),
    PROCESS_ERROR("96"),
    INVALID_ACCOUNT("14");

    private final String code;

    AuthorizationType(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}

