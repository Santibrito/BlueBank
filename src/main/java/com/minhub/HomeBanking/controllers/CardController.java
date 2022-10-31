package com.minhub.HomeBanking.controllers;

import com.minhub.HomeBanking.models.*;
import com.minhub.HomeBanking.repository.CardRepository;
import com.minhub.HomeBanking.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CardController {
    public int numberRandom(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;


    //anotacion con una peticion a ..., con un metodo post.
    @RequestMapping(path = "/api/clients/current/cards", method = RequestMethod.POST)

    //creamos un metodo con el nombre CreateCard, importamos los paremetros de cardtype y cardcolor con un requestparam para acceder al valor del parametro
    public ResponseEntity<Object> createCard(Authentication authentication,
                                             @RequestParam CardType cardType, @RequestParam CardColor cardColor ){

        //creamos un objeto de tipo cliente, obteniendo el cliente autenticado. que en este caso utilizamos el email.
        Client client = clientRepository.findByEmail(authentication.getName());

        //Creamos un list card
        List<Card> filterCards = client.getCards().stream().filter(card -> card.getType() == cardType).collect(Collectors.toList());

        if (filterCards.size() >= 3) {
            return new ResponseEntity<>("Llegaste al limite de tarjetas", HttpStatus.FORBIDDEN);
        }

        String cardCard = numberRandom(1000,9999) + "-" + numberRandom(1000,9999) + "-" + numberRandom(1000,9999) + "-" + numberRandom(1000,9999);

        Card card = new Card(client.getName() + " " + client.getLastName(), cardType, cardColor, cardCard, numberRandom(0, 999),LocalDate.now().plusYears(5),LocalDate.now(),client);
        cardRepository.save(card);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
