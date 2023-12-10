package com.mdp.petmed.Basket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasketServiceDTO {
    private Long basketId;
    private Long serviceId;
    private Integer quantity;

    public BasketServiceDTO(BasketServiceModel basketService){
        this.basketId = basketService.getBasket().getId();
        this.serviceId = basketService.getService().getId();
        this.quantity = basketService.getQuantity();
    }
}
