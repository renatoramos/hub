package br.com.hub.domain;

/**
 * Created by STELO\renato.5a on 8/8/18.
 */
public class TransactionAuthorization implements TransactionResponse{

    private String action;
    private String code;
    private String authorization_code;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthorization_code() {
        return authorization_code;
    }

    public void setAuthorization_code(String authorization_code) {
        this.authorization_code = authorization_code;
    }
}
