package com.minhub.HomeBanking.controllers;

import com.minhub.HomeBanking.dto.TransactionDTO;
import com.minhub.HomeBanking.models.Account;
import com.minhub.HomeBanking.models.Client;
import com.minhub.HomeBanking.models.Transaction;
import com.minhub.HomeBanking.models.TransactionType;
import com.minhub.HomeBanking.repository.AccountRepository;
import com.minhub.HomeBanking.repository.ClientRepository;
import com.minhub.HomeBanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.minhub.HomeBanking.models.TransactionType.CREDITO;
import static com.minhub.HomeBanking.models.TransactionType.DEBITO;

@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/api/accounts/transactions")
    public List<TransactionDTO> getTransaction(){
        return transactionRepository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }


    @Transactional
    @RequestMapping(path ="/api/transactions",method = RequestMethod.POST)
    public ResponseEntity<Object> createTransaction(Authentication authentication, @RequestParam Double amount,
                                                    @RequestParam String description,
                                                    @RequestParam String newAccountOrigen,
                                                    @RequestParam String newAccountDestiny) {


        Account accountOrigen = accountRepository.findByNumber(newAccountOrigen);
        Account accountDestiny = accountRepository.findByNumber(newAccountDestiny);
        Client currentClient = clientRepository.findByEmail(authentication.getName());


        if (amount <= 0 || description.isEmpty() || newAccountOrigen.isEmpty() || newAccountDestiny.isEmpty()) {
            return new ResponseEntity<>("Aún no tienes acceso a esta transacción", HttpStatus.FORBIDDEN);
        }

        if (accountDestiny.getNumber().equals(accountOrigen.getNumber())){
            return new ResponseEntity<>("No puedes enviar a la misma cuenta",HttpStatus.FORBIDDEN);
        }

        if (!currentClient.getAccounts().contains(accountOrigen)){
            return new ResponseEntity<>("No eres el dueño de la cuenta", HttpStatus.FORBIDDEN);
        }

        if (accountOrigen.getBalance() < amount){
            return new ResponseEntity<>("No tienes suficiente dinero en tu cuenta", HttpStatus.FORBIDDEN);
        }

        accountOrigen.setBalance(accountOrigen.getBalance() - amount);
        accountRepository.save(accountOrigen);

        accountDestiny.setBalance(accountDestiny.getBalance() + amount);
        accountRepository.save(accountDestiny);


        Transaction debitTransaction = new Transaction(DEBITO,-amount,description,LocalDateTime.now(),accountOrigen);
        transactionRepository.save(debitTransaction);

        Transaction creditTransaction = new Transaction(CREDITO,+amount,description,LocalDateTime.now(),accountDestiny);
        transactionRepository.save(creditTransaction);
        return new ResponseEntity<>("Transacción exitosa", HttpStatus.CREATED);

    }
}
