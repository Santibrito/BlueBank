package com.minhub.HomeBanking.repository;

import com.minhub.HomeBanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface ClientLoandRepository extends JpaRepository<ClientLoan, Long> {

}
