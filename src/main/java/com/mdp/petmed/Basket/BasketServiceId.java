package com.mdp.petmed.Basket;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class BasketServiceId {
    private Long basketId;
    private Long serviceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketServiceId that = (BasketServiceId) o;
        return Objects.equals(basketId, that.basketId) &&
                Objects.equals(serviceId, that.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, serviceId);
    }
}
