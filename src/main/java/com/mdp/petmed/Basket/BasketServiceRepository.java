package com.mdp.petmed.Basket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BasketServiceRepository extends JpaRepository<BasketServiceModel, BasketServiceModelId> {   
    @Transactional
    @Modifying
    @Query("DELETE FROM BasketServiceModel bs " +
           "WHERE bs.basket.id = :basketId AND bs.service.id = :serviceId")
    void deleteByBasketIdAndServiceId(@Param("basketId") Long basketId, @Param("serviceId") Long serviceId);

    @Transactional
    List<BasketServiceModel> findByBasketId(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM BasketServiceModel bs " +
           "WHERE bs.basket.id = :basketId")
    void deleteByBasketId(Long basketId);
}
