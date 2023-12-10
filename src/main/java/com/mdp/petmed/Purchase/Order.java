package com.mdp.petmed.Purchase;

import java.util.Date;
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
@Table(name="tbl_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private Double price;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tbl_order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderService> orderServices;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Date date, Double price, User user){
        this.date = date;
        this.price = price;
        this.user = user;
    }
}
