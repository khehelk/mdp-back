package com.mdp.petmed.Order;

import java.util.Date;
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
@Table(name="tbl_order")
public class OrderModel {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private Double price;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tbl_order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderServiceModel> orderServices;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public OrderModel(Date date, Double price, UserModel user){
        this.date = date;
        this.price = price;
        this.user = user;
    }
}
