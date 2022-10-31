package com.minhub.HomeBanking.dto;

import com.minhub.HomeBanking.models.ClientLoan;

public class ClientLoanDTO {

    private long id;
    private long idLoan;
    private String name;
    private double amount;
    private int payments;

    public ClientLoanDTO(ClientLoan clientLoan) {

        this.idLoan = clientLoan.getLoan().getId();

        this.name = clientLoan.getLoan().getName();

        this.amount = clientLoan.getAmount();

        this.payments = clientLoan.getPayments();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdLoan() {
        return idLoan;
    }

    public void setIdLoan(long idLoan) {
        this.idLoan = idLoan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }
}
