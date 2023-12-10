package com.mdp.petmed.User;

import java.util.List;

import com.mdp.petmed.Basket.BasketModel;
import com.mdp.petmed.Order.OrderModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_user")
public class UserModel {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private RoleEnum role;
    private byte[] photo;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<OrderModel> orders;
    @OneToOne
    private BasketModel basket;

    public UserModel(String name, String surname, String email, String password, byte[] photo, RoleEnum role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.role = role;
    }
}
