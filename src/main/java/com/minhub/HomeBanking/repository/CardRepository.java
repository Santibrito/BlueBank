package com.minhub.HomeBanking.repository;

import com.minhub.HomeBanking.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository <Card, Long> {
}
