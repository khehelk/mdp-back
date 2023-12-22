package com.mdp.petmed.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_service")
public class ServiceModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private int photo;

    public ServiceModel(String name, Double price, int photo){
        this.name = name;
        this.price = price;
        this.photo = photo;
    }
}
