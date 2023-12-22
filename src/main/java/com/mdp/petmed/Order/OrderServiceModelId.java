package com.mdp.petmed.Order;

import java.io.Serializable;

import com.mdp.petmed.Service.ServiceModel;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class OrderServiceModelId implements Serializable {    
    private Long orderId;    
    private Long serviceId;

    public OrderServiceModelId(OrderModel order, ServiceModel service){
        super();
        this.orderId = order.getId();
        this.serviceId = service.getId();
    }

    public OrderServiceModelId(){

    }
}
