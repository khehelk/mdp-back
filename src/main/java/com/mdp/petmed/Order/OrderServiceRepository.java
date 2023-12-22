package com.mdp.petmed.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderServiceRepository extends JpaRepository<OrderServiceModel, OrderServiceModelId> {   
    @Transactional
    @Modifying
    @Query("DELETE FROM OrderServiceModel bs " +
           "WHERE bs.order.id = :orderId AND bs.service.id = :serviceId")
    void deleteByOrderIdAndServiceId(@Param("orderId") Long orderId, @Param("serviceId") Long serviceId);
}
