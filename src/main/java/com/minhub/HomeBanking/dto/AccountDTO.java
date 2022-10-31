package com.minhub.HomeBanking.dto;

import com.minhub.HomeBanking.models.Account;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;
    private String number;
    private double balance;
    private LocalDateTime creationDate;

    public AccountDTO() {
    }

    private Set<TransactionDTO> transaction = new HashSet<>();

    public AccountDTO(Account account) {

        this.id = account.getId();

        this.number = account.getNumber();

        this.balance = account.getBalance();

        this.creationDate = account.getCreationDate();

        this.transaction = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<TransactionDTO> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<TransactionDTO> transaction) {
        this.transaction = transaction;
    }
}


