package com.mdp.petmed.Basket;

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
@Table(name="tbl_basket_service")
public class BasketServiceModel {
    @EmbeddedId
    private BasketServiceId id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name="basket_id")
    private BasketModel basket;
    @ManyToOne
    @JoinColumn(name="service_id")
    private ServiceModel service;

    public BasketServiceModel(BasketModel basket, ServiceModel service, int quantity) {
        this.id = new BasketServiceId(basket.getId(), service.getId());
        this.basket = basket;
        this.service = service;
        this.quantity = quantity;
    }
}
