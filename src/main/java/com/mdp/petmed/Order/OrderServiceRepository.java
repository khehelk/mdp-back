package com.mdp.petmed.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepository extends JpaRepository<OrderService, OrderServiceId>{
    
}
