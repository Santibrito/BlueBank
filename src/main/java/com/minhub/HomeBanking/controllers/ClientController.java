package com.minhub.HomeBanking.controllers;
import com.minhub.HomeBanking.dto.ClientDTO;
import com.minhub.HomeBanking.models.Account;
import com.minhub.HomeBanking.models.Client;
import com.minhub.HomeBanking.repository.AccountRepository;
import com.minhub.HomeBanking.repository.ClientRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/api/clients")
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @RequestMapping("/api/clients/{id}")
    public ClientDTO getClients(@PathVariable Long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @RequestMapping("api/clients/current")
    public ClientDTO getClients(Authentication authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    };



    @Autowired
    private PasswordEncoder passwordEncoder;


    public int numberRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

        @RequestMapping(path = "/api/clients", method = RequestMethod.POST)

        public ResponseEntity<Object> register( //Esto es una varibale publica

                @RequestParam String name, @RequestParam String lastName,

                @RequestParam String email, @RequestParam String password,

                Authentication authentication //averiguar porque sirve
        ) {


            if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

                return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

            }


            if (clientRepository.findByEmail(email) != null) {

                return new ResponseEntity<>("Email ya esta en uso", HttpStatus.FORBIDDEN);

            }

            Client client = new Client(name, lastName, email, passwordEncoder.encode(password));
            clientRepository.save(client);

            Account account = new Account("VIN - " + numberRandom(0, 999999999), 0, LocalDateTime.now(), client);
            accountRepository.save(account);

            return new ResponseEntity<>(HttpStatus.CREATED);

    }


}
