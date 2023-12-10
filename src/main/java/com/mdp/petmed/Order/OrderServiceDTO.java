package com.mdp.petmed.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderServiceDTO {
    private Long orderId;
    private Long serviceId;
    private Integer quantity;

    public OrderServiceDTO(OrderServiceModel orderService){
        this.orderId = orderService.getOrder().getId();
        this.serviceId = orderService.getService().getId();
        this.quantity = orderService.getQuantity();
    }
}
