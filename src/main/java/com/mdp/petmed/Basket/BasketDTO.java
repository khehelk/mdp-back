package com.mdp.petmed.Basket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasketDTO {
    private Long id;
    private Long userId;

    public BasketDTO(BasketModel basket){
        this.id = basket.getId();
        this.userId = basket.getUser().getId();
    }
}
