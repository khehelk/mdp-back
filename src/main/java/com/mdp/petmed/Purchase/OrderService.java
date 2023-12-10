package com.mdp.petmed.Purchase;

import java.util.Date;
import java.util.List;

import com.mdp.petmed.Service.Service;
import com.mdp.petmed.User.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_order_service")
public class OrderService {
    @EmbeddedId
    private OrderServiceId id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;

    public OrderService(Order order, Service service, int quantity) {
        this.id = new OrderServiceId(order.getId(), service.getId());
        this.order = order;
        this.service = service;
        this.quantity = quantity;
    }
}
