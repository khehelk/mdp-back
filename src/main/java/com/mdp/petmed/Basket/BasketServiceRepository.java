package com.mdp.petmed.Basket;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface BasketServiceRepository extends JpaRepository<BasketServiceModel, BasketServiceId> {
    @Transactional
    void deleteByBasketIdAndServiceId(Long basketId, Long serviceId);

    @Transactional
    void deleteByBasketId(Long basketId);
}
