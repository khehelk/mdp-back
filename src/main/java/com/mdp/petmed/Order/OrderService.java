package com.mdp.petmed.Order;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdp.petmed.Basket.BasketService;
import com.mdp.petmed.Basket.BasketServiceModel;
import com.mdp.petmed.Service.ServiceModel;
import com.mdp.petmed.Service.ServiceService;
import com.mdp.petmed.User.UserModel;
import com.mdp.petmed.User.UserRepository;
import com.mdp.petmed.User.UserService;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderServiceRepository orderServiceRepository;
    private final ServiceService serviceService;
    private final BasketService basketService;

    public OrderService(OrderRepository orderRepository, UserService userService, 
    OrderServiceRepository orderServiceRepository, ServiceService serviceService, BasketService basketService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderServiceRepository = orderServiceRepository;
        this.serviceService = serviceService;
        this.basketService = basketService;
    }

    @Transactional(readOnly = true)
    public OrderModel getById(Long id){
        return orderRepository.getReferenceById(id);
    }

    @Transactional
    public void createOrder(OrderDTO orderDTO){
        var user = userService.getUserById(orderDTO.getCreatorUserId());
        OrderModel order = new OrderModel(orderDTO.getDate(), orderDTO.getTotal(), user);
        order = orderRepository.save(order);
        List<BasketServiceModel> basketItems = basketService.findByBasketServiceId(user.getBasket().getId());
        for (BasketServiceModel basketItem : basketItems) {
            ServiceModel service = basketItem.getService();
            int quantity = basketItem.getQuantity();
            OrderServiceModel orderServiceItem = new OrderServiceModel(order, service, quantity);
            orderServiceRepository.save(orderServiceItem);
        }
        basketService.deleteAllServiceFromBasket(user.getBasket().getId());        
    }

    @Transactional
    public OrderServiceModel addServiceToOrder(OrderServiceDTO orderService){
        return orderServiceRepository.save(new OrderServiceModel(
            getById(orderService.getOrderId()), 
            serviceService.getById(orderService.getServiceId()), 
            orderService.getQuantity()));
    }

    @Transactional
    public List<OrderModel> getUserOrders(Long userId){
        UserModel user = userService.getUserById(userId);
        return user.getOrders();
    }

    @Transactional
    public List<ServiceModel> getServiceFromOrder(Long orderId){
        OrderModel order = orderRepository.getReferenceById(orderId);
        return order.getOrderServices()
                .stream()
                .map(orderService -> new ServiceModel(orderService.getService().getName(), orderService.getService().getPrice(), orderService.getService().getPhoto()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrder(Long orderId){
        orderRepository.delete(getById(orderId));
    }
}