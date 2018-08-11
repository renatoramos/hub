package br.com.hub.domain;

import com.google.common.collect.EvictingQueue;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by STELO\renato.5a on 8/9/18.
 */
@RedisHash("Card")
public class Card implements Serializable, TransactionResponse {
    @Id
    private String cardnumber;
    private Double availableAmount;
    private List<TransactionHistory> transactions = new ArrayList<>();

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public List<TransactionHistory> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionHistory> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(TransactionHistory transactionHistory) {
        EvictingQueue<TransactionHistory> queue = EvictingQueue.create(10);
        queue.addAll(this.transactions);
        queue.add(transactionHistory);
        this.transactions = new ArrayList<>(queue);
    }
}

