package com.mdp.petmed.Basket;

import java.util.List;

import com.mdp.petmed.Service.ServiceModel;
import com.mdp.petmed.User.UserModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_basket_service")
public class BasketServiceModel {
    @EmbeddedId
    private BasketServiceModelId id = new BasketServiceModelId();
    @ManyToOne
    @MapsId("basketId")
    BasketModel basket;
    @ManyToOne
    @MapsId("serviceId")
    ServiceModel service;
    private int quantity;    

    public BasketServiceModel(BasketModel basket, ServiceModel service, int quantity) {    
        this.id = new BasketServiceModelId(basket, service);
        this.basket = basket;
        this.service = service;
        this.quantity = quantity;
    }
}
