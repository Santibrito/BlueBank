package com.minhub.HomeBanking.dto;



import com.minhub.HomeBanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ClientDTO {
    private long id;
    private String name;
    private String lastName;
    private String email;

    private Set<AccountDTO> accounts = new HashSet<>();
    private Set<ClientLoanDTO> loan = new HashSet<>();

    private Set<CardDTO> card = new HashSet<>();


    public ClientDTO() {
    }

    public ClientDTO(Client client) {

        this.id = client.getId();

        this.name = client.getName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());

        this.loan = client.getClientLoans().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());

        this.card = client.getCards().stream().map(CardDTO::new).collect(Collectors.toSet());

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loan;
    }

    public void setLoans(Set<ClientLoanDTO> loans) {
        this.loan = loans;
    }

    public Set<ClientLoanDTO> getLoan() {
        return loan;
    }

    public void setLoan(Set<ClientLoanDTO> loan) {
        this.loan = loan;
    }

    public Set<CardDTO> getCard() {
        return card;
    }

    public void setCard(Set<CardDTO> card) {
        this.card = card;
    }
}


