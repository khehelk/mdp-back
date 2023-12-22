package com.mdp.petmed.Basket;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdp.petmed.Service.ServiceService;
import com.mdp.petmed.User.UserModel;
import com.mdp.petmed.User.UserService;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketServiceRepository basketServiceRepository;
    private final UserService userService;
    private final ServiceService serviceService;

    public BasketService(
        BasketRepository basketRepository, 
        UserService userRepository,
        ServiceService serviceService,
        BasketServiceRepository basketServiceRepository
    ) {
        this.basketRepository = basketRepository;
        this.userService = userRepository;
        this.serviceService = serviceService;
        this.basketServiceRepository = basketServiceRepository;
    }

    @Transactional
    public BasketModel getById(Long id){
        return basketRepository.getReferenceById(id);
    }

    @Transactional
    public BasketModel create(UserModel user){
        BasketModel basket = new BasketModel(user);
        user.setBasket(basket);
        return basketRepository.save(basket);
    }

    @Transactional
    public List<BasketServiceModel> findByBasketServiceId(Long id){
        return basketServiceRepository.findByBasketId(id);
    }

    @Transactional
    public void addServiceToBasket(BasketServiceDTO basketServiceDto){
        basketServiceRepository.save(new BasketServiceModel(
            getById(basketServiceDto.getBasketId()),
            serviceService.getById(basketServiceDto.getServiceId()),
            basketServiceDto.getQuantity()
        ));
    }

    @Transactional
    public BasketModel getUserBasket(Long id){
        UserModel user = userService.getUserById(id);
        return user.getBasket();
    }

    @Transactional
    public List<BasketServiceModel> getUserBasketServices(Long id){
        UserModel user = userService.getUserById(id);
        BasketModel basket = user.getBasket();
        return basket.getBasketServices();
    }

    @Transactional
    public int getQuantity(Long basketId, Long serviceId){
        BasketModel basket = basketRepository.getReferenceById(basketId);
        List<BasketServiceModel> basketServices = basket.getBasketServices();

        Optional<BasketServiceModel> matchingBasketService = basketServices.stream()
                .filter(basketService -> basketService.getService().getId().equals(serviceId))
                .findFirst();
        return matchingBasketService.map(BasketServiceModel::getQuantity).orElse(0);
    }

    @Transactional
    public void incrementQuantity(Long basketId, Long serviceId){
        basketRepository.incrementServiceQuantity(basketId, serviceId);
    }

    @Transactional
    public void decrementQuantity(Long basketId, Long serviceId){
        basketRepository.decrementServiceQuantity(basketId, serviceId);
    }

    @Transactional
    public boolean getService(Long basketId, Long serviceId){
        return basketRepository.existsService(basketId, serviceId);
    }

    @Transactional
    public void removeServiceFromBasket(Long basketId, Long serviceId){
        basketServiceRepository.deleteByBasketIdAndServiceId(basketId, serviceId);
    }

    @Transactional
    public double getTotalPriceForUserBasket(Long userId){
        return basketRepository.getTotalPriceForUser(userId);
    }

    @Transactional
    public void deleteAllServiceFromBasket(Long basketId){
        basketServiceRepository.deleteByBasketId(basketId);
    }
}
