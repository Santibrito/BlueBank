package com.minhub.HomeBanking.dto;

import com.minhub.HomeBanking.models.Account;
import com.minhub.HomeBanking.models.Client;
import com.minhub.HomeBanking.models.Transaction;
import com.minhub.HomeBanking.models.TransactionType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class TransactionDTO {
    private long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDateTime date;

    private Set<ClientDTO> clients = new HashSet<>();

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {

        this.id = transaction.getId();

        this.type = transaction.getType();

        this.amount = transaction.getAmount();

        this.description = transaction.getDescription();

        this.date = transaction.getDate();

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(Set<ClientDTO> clients) {
        this.clients = clients;
    }


}
