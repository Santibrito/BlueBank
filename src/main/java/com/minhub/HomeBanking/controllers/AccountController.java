package com.minhub.HomeBanking.controllers;

import com.minhub.HomeBanking.dto.AccountDTO;

import com.minhub.HomeBanking.models.Account;
import com.minhub.HomeBanking.models.Client;
import com.minhub.HomeBanking.repository.AccountRepository;
import com.minhub.HomeBanking.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;



@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
//Permite inyectar dependencias unas con otras
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/api/accounts")
    public List<AccountDTO> getAccount(){
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());

    }

    @RequestMapping("/api/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    //Metodos
    public int numberRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }//estudiar esto

    //empieza por aca
    @RequestMapping(path = "/api/clients/current/accounts", method = RequestMethod.POST)

    //Esto es un metodo de nombre create Account
    //Una respuesta http
    public ResponseEntity<Object> createAccount(Authentication authentication) {

        //Creo un objeto de tipo client.
        //clietnerepositorory lo importo desde el autowired
        //clientreposiroty.findbyemail = busca dentro del repositorio el cliente autenticado que en
        //este caso lo autenticamos por el email
        Client client = clientRepository.findByEmail(authentication.getName()); //objeto

        //Condicional, que si en el objeto cliente, es mayor o igual a 3.
        if (client.getAccounts().size() >= 3) {
            //te retorna una respuesta forbidden con un mesnaje diciendo que llegaste al limite.
            return new ResponseEntity<>("Llegaste al limite", HttpStatus.FORBIDDEN);
        }

        //Y Si el cliente tiene menos de 3 cuentas entonces
        //crea dentro de account repository una nueva cuenta, vinculandola al cliente anteriormente autenticado,
        accountRepository.save(new Account("VIN" + numberRandom(0, 999999999), 0, LocalDateTime.now(), client));

        //retronando una status created.
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
