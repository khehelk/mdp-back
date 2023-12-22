package com.mdp.petmed.Order;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdp.petmed.Service.ServiceDTO;
import com.mdp.petmed.Service.ServiceService;

@RestController
@RequestMapping({"api/order"})
public class OrderController {
    private final OrderService orderService;
    private final ServiceService serviceService;

    public OrderController(OrderService orderService, ServiceService serviceService) {
        this.orderService = orderService;
        this.serviceService = serviceService;
    }

    @PostMapping("/addServiceToOrder")
    public void addServiceToOrder(@RequestBody OrderServiceDTO orderServiceDTO){
        orderService.addServiceToOrder(orderServiceDTO);
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);        
    }

    @GetMapping("/getUserOrders/{userId}")
    public List<OrderDTO> getUserOrders(@PathVariable("userId") Long userId){
        return orderService.getUserOrders(userId).stream().map(OrderDTO::new).toList();
    }

    @GetMapping("/getServiceFromOrder/{orderId}")
    public List<ServiceDTO> getServiceFromOrder(@PathVariable("orderId") Long orderId){
        return orderService.getServiceFromOrder(orderId).stream().map(ServiceDTO::new).toList();
    }

    @GetMapping("/deleteOrder/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
    }
}
