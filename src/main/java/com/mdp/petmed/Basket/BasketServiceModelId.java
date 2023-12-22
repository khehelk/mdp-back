package com.mdp.petmed.Basket;

import com.mdp.petmed.Service.ServiceModel;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class BasketServiceModelId implements Serializable {    
    private Long basketId;    
    private Long serviceId;

    public BasketServiceModelId(BasketModel basket, ServiceModel service){
        super();
        this.basketId = basket.getId();
        this.serviceId = service.getId();
    }

    public BasketServiceModelId(){

    }
}
