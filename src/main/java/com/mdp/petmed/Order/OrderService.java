package com.mdp.petmed.Order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdp.petmed.Service.ServiceModel;
import com.mdp.petmed.User.UserModel;
import com.mdp.petmed.User.UserRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderServiceRepository orderServiceRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderServiceRepository orderServiceRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderServiceRepository = orderServiceRepository;
    }

    @Transactional(readOnly = true)
    public OrderModel findOrderById(Long id){
        return orderRepository.getReferenceById(id);
    }

    @Transactional
    public OrderModel createOrder(OrderDTO orderDTO){
        OrderModel order = new OrderModel(orderDTO.getDate(), orderDTO.getCity(), orderDTO.getStreet(), orderDTO.getHouse(), orderDTO.getSubtotal(), orderDTO.getTaxes(), orderDTO.getTotal(), userRepository.getReferenceById(orderDTO.getUserId()));
        return orderRepository.save(order);
    }

    @Transactional
    public OrderServiceModel createServiceOrder(OrderServiceModel orderService){
        return orderServiceRepository.save(orderService);
    }

    @Transactional
    public List<OrderModel> getUserOrders(Long userId){
        UserModel user = userRepository.getReferenceById(userId);
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
        orderRepository.delete(findOrderById(orderId));
    }
}