package com.mdp.petmed.Order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceId implements Serializable{
    private Long orderId;
    private Long serviceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderServiceId that = (OrderServiceId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, serviceId);
    }
}
