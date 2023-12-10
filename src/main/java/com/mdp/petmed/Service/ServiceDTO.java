package com.mdp.petmed.Service;

import java.nio.charset.StandardCharsets;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceDTO {
    private Long id;
    private String name;
    private Double price;
    private String photo;

    public ServiceDTO(ServiceModel service) {
        this.id = service.getId();
        this.name = service.getName();
        this.price = service.getPrice();
        this.photo = new String(service.getPhoto(), StandardCharsets.UTF_8);
    }
}
