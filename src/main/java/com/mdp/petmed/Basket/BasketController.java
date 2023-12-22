package com.mdp.petmed.Basket;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdp.petmed.Service.ServiceDTO;

@RestController
@RequestMapping("api/basket")
public class BasketController {
    private final BasketService basketService;    
    
    public BasketController(BasketService basketService) {
        this.basketService = basketService;        
    }

    @PostMapping("/addServiceToBasket")
    public void addServiceToBasket(@RequestBody BasketServiceDTO basketServiceDTO){    
        basketService.addServiceToBasket(basketServiceDTO);
    }

    @GetMapping("/getUserBasket/{id}")
    public Long getUserBasket(@PathVariable("id") Long id){
        return basketService.getUserBasket(id).getId();
    }

    @GetMapping("/getUserBasketServices/{id}")
    public List<ServiceDTO> getUserBasketServices(@PathVariable("id") Long id) {
        return basketService.getUserBasketServices(id)
                .stream()
                .map(bs -> new ServiceDTO(bs.getService()))
                .distinct()
                .toList();
    }

    @GetMapping("/getQuantity/{basketId}/{serviceId}")
    public Integer getQuantity(@PathVariable("basketId") Long basketId,
                               @PathVariable("serviceId") Long serviceId
    ){
        return basketService.getQuantity(basketId, serviceId);
    }

    @PutMapping("/incrementQuantity/{basketId}/{serviceId}")
    public void incrementQuantity(@PathVariable("basketId") Long basketId,
                                  @PathVariable("serviceId") Long serviceId
    ){
        basketService.incrementQuantity(basketId, serviceId);
    }

    @PutMapping("/decrementQuantity/{basketId}/{serviceId}")
    public void decrementQuantity(@PathVariable("basketId") Long basketId,
                                  @PathVariable("serviceId") Long serviceId
    ){
        basketService.decrementQuantity(basketId, serviceId);
    }

    @GetMapping("/getService/{basketId}/{serviceId}")
    public boolean getService(@PathVariable("basketId") Long basketId,
                                       @PathVariable("serviceId") Long serviceId
    ){
        return basketService.getService(basketId, serviceId);
    }

    @GetMapping("/removeService/{basketId}/{serviceId}")
    public void removeServiceFromBasket(@PathVariable("basketId") Long basketId,
                                        @PathVariable("serviceId") Long serviceId
    ){
        basketService.removeServiceFromBasket(basketId, serviceId);
    }

    @GetMapping("/getUserPrice/{userId}")
    public double getTotalPriceForUserBasket(@PathVariable("userId") Long userId){
        return basketService.getTotalPriceForUserBasket(userId);
    }

    @GetMapping("/deleteAllServiceFromBasket/{basketId}")
    public void deleteAllServiceFromBasket(@PathVariable("basketId") Long basketId){
        basketService.deleteAllServiceFromBasket(basketId);
    }
}
