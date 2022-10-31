package com.minhub.HomeBanking.controllers;

import com.minhub.HomeBanking.dto.ClientLoanDTO;
import com.minhub.HomeBanking.repository.ClientLoandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

public class ClientLoanController {
    @Autowired
    private ClientLoandRepository clientLoandRepository;

    @RequestMapping("/api/clients")
    public List<ClientLoanDTO> getClients(){
        return clientLoandRepository.findAll().stream().map(ClientLoanDTO::new).collect(Collectors.toList());
    }


    @RequestMapping("/api/clients/{id}")
    public ClientLoanDTO getClientsLoan(@PathVariable Long id){
        return clientLoandRepository.findById(id).map(ClientLoanDTO::new).orElse(null);
    }
}
