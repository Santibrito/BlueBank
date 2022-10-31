package com.minhub.HomeBanking;

import com.minhub.HomeBanking.models.*;
import com.minhub.HomeBanking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;


import static com.minhub.HomeBanking.models.TransactionType.CREDITO;
import static com.minhub.HomeBanking.models.TransactionType.DEBITO;


@SpringBootApplication
public class HomeBankingApplication {


	public static void main(String[] args) {

		SpringApplication.run(HomeBankingApplication.class, args);

	}

	@Autowired
	PasswordEncoder PasswordEncoder;


	@Bean
	CommandLineRunner runner(ClientRepository repository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoandRepository clienLoandRepository, CardRepository cardRepository){
		return

				args -> {

			Client admin = new Client("admin","admin","admin",PasswordEncoder.encode("admin"));
			repository.save(admin);

			/*Melba*/
			Client client1 = new Client("Melba","Lorenzo","melbalorenzo@gmail.com",PasswordEncoder.encode("melba123"));
			repository.save(client1);

			Account account1 = new Account("VIN001",200,LocalDateTime.now(),client1);
			accountRepository.save(account1);

            Account account2 = new Account("VIN002",7500,LocalDateTime.now().plusDays(1),client1);
			accountRepository.save(account2);

			Transaction transactions1 = new Transaction(DEBITO,-17500,"Alquiler",LocalDateTime.now(),account1);
			transactionRepository.save(transactions1);

			Transaction transactions2 = new Transaction(CREDITO,500,"Impuesto",LocalDateTime.now(),account1);
			transactionRepository.save(transactions2);

			Transaction transactions3 = new Transaction(CREDITO,5000,"Licuadora",LocalDateTime.now(),account2);
			transactionRepository.save(transactions3);

			Transaction transactions4 = new Transaction(DEBITO,3500,"Expensas",LocalDateTime.now(),account2);
			transactionRepository.save(transactions4);

			Loan loan1 = new Loan("Hipotecario",500.000, Arrays.asList(12, 24, 36, 48, 60));
			loanRepository.save(loan1);

			Loan loan2 = new Loan("Personal",100.000, Arrays.asList(6, 12, 24));
			loanRepository.save(loan2);

			Loan loan3 = new Loan("Automotriz",300.000, Arrays.asList(6, 12, 24, 36));
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(2000, loan1.getPayments().get(1), client1, loan1);
			clienLoandRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(9000, loan2.getPayments().get(2), client1, loan2);
			clienLoandRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(12000, loan3.getPayments().get(3), client1, loan3);
			clienLoandRepository.save(clientLoan3);

			Card clientCard1 = new Card(client1.getName() + " " + client1.getLastName(),CardType.DEBITO,CardColor.GOLD,"4003  1362  0767  3394",123,LocalDate.now().plusYears(5),LocalDate.now(),client1);
			cardRepository.save(clientCard1);

			Card clientCard2 = new Card(client1.getName() + " " + client1.getLastName(),CardType.CREDITO,CardColor.TITANIUM,"4014  4875  1252  3212",538,LocalDate.now().plusYears(5),LocalDate.now(),client1);
			cardRepository.save(clientCard2);

			/*Luna*/
			Client client2 = new Client("Luna","Sbuttoni","lunasbuttoni@gmail.com",PasswordEncoder.encode("luna123"));
			repository.save(client2);

			Account account3 = new Account("VIN003",600,LocalDateTime.now(),client2);
			accountRepository.save(account3);

			Account account4 = new Account("VIN004",9200,LocalDateTime.now().plusDays(1),client2);
			accountRepository.save(account4);


			Transaction transactions5 = new Transaction(DEBITO,-14500,"Alquiler",LocalDateTime.now(),account1);
			transactionRepository.save(transactions5);

			Transaction transactions6 = new Transaction(CREDITO,2500,"Impuesto",LocalDateTime.now(),account1);
			transactionRepository.save(transactions6);

			Transaction transactions7 = new Transaction(CREDITO,500,"Licuadora",LocalDateTime.now(),account2);
			transactionRepository.save(transactions7);

			Transaction transactions8 = new Transaction(DEBITO,35500,"Expensas",LocalDateTime.now(),account2);
			transactionRepository.save(transactions8);

			Loan loan4 = new Loan("Hipotecario",500.000, Arrays.asList(12, 24, 36, 48, 60));
			loanRepository.save(loan4);

			Loan loan5 = new Loan("Personal",100.000, Arrays.asList(6, 12, 24));
			loanRepository.save(loan5);

			Loan loan6 = new Loan("Automotriz",300.000, Arrays.asList(6, 12, 24, 36));
			loanRepository.save(loan6);

			ClientLoan clientLoan5 = new ClientLoan(2000, loan1.getPayments().get(1), client2, loan4);
			clienLoandRepository.save(clientLoan5);

			Card clientCard3 = new Card(client2.getName() + " " + client2.getLastName(),CardType.CREDITO,CardColor.SILVER,"4003  1123  0767  3394",199,LocalDate.now().plusYears(5),LocalDate.now(),client2);
			cardRepository.save(clientCard3);

		};
	}
}
