package com.mdp.petmed.Order;

import java.nio.charset.StandardCharsets;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Long date;
    private Double total;
    private Long creatorUserId;

    public OrderDTO(OrderModel order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.total = order.getTotal();
        this.creatorUserId = order.getUser().getId();
    }
}
