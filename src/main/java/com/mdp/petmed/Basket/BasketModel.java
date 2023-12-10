package com.mdp.petmed.Basket;

import java.util.List;

import com.mdp.petmed.User.UserModel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_basket")
public class BasketModel {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tbl_basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketServiceModel> basketServices;
    @OneToOne
    private UserModel user;

    public BasketModel(UserModel user){
        this.user = user;
    }
}
