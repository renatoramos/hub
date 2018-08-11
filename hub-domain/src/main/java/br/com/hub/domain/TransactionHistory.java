package br.com.hub.domain;

import java.time.LocalDateTime;

/**
 * Created by STELO\renato.5a on 8/9/18.
 */
public class TransactionHistory {

    private LocalDateTime date;
    private Double amout;
    private String authorizationCode;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getAmout() {
        return amout;
    }

    public void setAmout(Double amout) {
        this.amout = amout;
    }
}
