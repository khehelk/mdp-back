package com.mdp.petmed.Basket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mdp.petmed.Service.ServiceModel;

import jakarta.transaction.Transactional;
import java.util.List;


public interface BasketRepository extends JpaRepository<BasketModel, Long> {   
    @Modifying
    @Transactional
    @Query("UPDATE BasketServiceModel bs SET bs.quantity = bs.quantity + 1 WHERE bs.basket.id = :basketId AND bs.service.id = :serviceId")
    void incrementServiceQuantity(@Param("basketId") Long basketId, @Param("serviceId") Long serviceId);

    @Modifying
    @Transactional
    @Query("UPDATE BasketServiceModel bs SET bs.quantity = bs.quantity - 1 WHERE bs.basket.id = :basketId AND bs.service.id = :serviceId")
    void decrementServiceQuantity(@Param("basketId") Long basketId, @Param("serviceId") Long serviceId);

    @Transactional
    @Query("SELECT COUNT(bs) > 0 FROM BasketServiceModel bs WHERE bs.basket.id = :basketId AND bs.service.id = :serviceId")
    boolean existsService(@Param("basketId") Long basketId, @Param("serviceId") Long serviceId);

    @Query("SELECT COALESCE(SUM(bs.service.price * bs.quantity), 0) FROM BasketServiceModel bs WHERE bs.basket.id = :userId")
    double getTotalPriceForUser(@Param("userId") Long userId);
}
