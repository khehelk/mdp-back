package com.mdp.petmed.Purchase;

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
@Table(name="tbl_basket_service")
public class BasketService {
    @EmbeddedId
    private BasketServiceId id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="basket_id")
    private Basket basket;
    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;

    public BasketService(Basket basket, Service service, int quantity) {
        this.id = new BasketServiceId(basket.getId(), service.getId());
        this.basket = basket;
        this.service = service;
        this.quantity = quantity;
    }
}
