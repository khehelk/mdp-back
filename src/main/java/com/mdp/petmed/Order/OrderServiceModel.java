package com.mdp.petmed.Order;

import java.util.Date;
import java.util.List;

import com.mdp.petmed.Service.ServiceModel;
import com.mdp.petmed.User.UserModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_order_service")
public class OrderServiceModel {
    @EmbeddedId
    private OrderServiceId id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderModel order;
    @ManyToOne
    @JoinColumn(name="service_id")
    private ServiceModel service;

    public OrderServiceModel(OrderModel order, ServiceModel service, int quantity) {
        this.id = new OrderServiceId(order.getId(), service.getId());
        this.order = order;
        this.service = service;
        this.quantity = quantity;
    }
}
