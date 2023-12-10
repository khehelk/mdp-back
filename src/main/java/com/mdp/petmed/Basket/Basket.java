package com.mdp.petmed.Basket;

import java.util.List;

import com.mdp.petmed.User.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_basket")
public class Basket {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tbl_basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketService> basketServices;
    @OneToOne
    private User user;

    public Basket(User user){
        this.user = user;
    }
}
