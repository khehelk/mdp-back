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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long date;
    private Double total;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderServiceModel> orderServices;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public OrderModel(Long date, Double total, UserModel user){
        this.date = date;
        this.total = total;
        this.user = user;
    }
}
