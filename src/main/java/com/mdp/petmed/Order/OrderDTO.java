package com.mdp.petmed.Order;

import java.nio.charset.StandardCharsets;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Long date;
    private Double price;
    private Long userId;

    public OrderDTO(OrderModel order) {
        this.id = order.getId();
        this.date = order.getDate().getTime();
        this.price = order.getPrice();
        this.userId = order.getUser().getId();
    }
}
