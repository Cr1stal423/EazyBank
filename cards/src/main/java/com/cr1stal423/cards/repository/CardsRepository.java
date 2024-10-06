package com.cr1stal423.cards.repository;

import com.cr1stal423.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findCardsByMobileNumber(String mobileNumber);
}
