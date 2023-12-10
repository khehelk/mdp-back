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
public class Service {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private byte[] photo;

    public Service(String name, Double price, byte[] photo){
        this.name = name;
        this.price = price;
        this.photo = photo;
    }
}
