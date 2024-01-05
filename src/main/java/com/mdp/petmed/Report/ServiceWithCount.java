package com.mdp.petmed.Report;

import com.mdp.petmed.Service.ServiceDTO;
import com.mdp.petmed.Service.ServiceModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceWithCount {
    private ServiceDTO service;
    private Long quantity;

    public ServiceWithCount(ServiceDTO service, Long quantity){
        this.service = service;
        this.quantity = quantity;
    }

    public ServiceWithCount(Object[] objects) {
        this.service = new ServiceDTO((ServiceModel) objects[0]);
        this.quantity = (Long) objects[1];
    }
}
