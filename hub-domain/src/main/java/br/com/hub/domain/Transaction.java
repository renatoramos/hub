package br.com.hub.domain;

/**
 * Created by STELO\renato.5a on 8/8/18.
 */
public class Transaction {

    private String action;
    private String cardnumber;
    private Double amount;

    public Transaction(String action, String cardnumber, Double amount) {
        this.action = action;
        this.cardnumber = cardnumber;
        this.amount = amount;
    }

    public Transaction() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "action='" + action + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
