package com.chalanimantech.onlinegroceryshopping.repository;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    Optional<Offer> findByProduct_Id(String id);
}
