package com.mdp.petmed.Order;

import java.util.Date;
import java.util.List;

import com.mdp.petmed.Basket.BasketModel;
import com.mdp.petmed.Basket.BasketServiceModelId;
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
    private OrderServiceModelId id = new OrderServiceModelId();
    @ManyToOne
    @MapsId("orderId")
    OrderModel order;
    @ManyToOne
    @MapsId("serviceId")
    ServiceModel service;
    private int quantity;    

    public OrderServiceModel(OrderModel order, ServiceModel service, int quantity) {    
        this.id = new OrderServiceModelId(order, service);
        this.order = order;
        this.service = service;
        this.quantity = quantity;
    }
}

