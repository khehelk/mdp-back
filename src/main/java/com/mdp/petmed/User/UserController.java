package com.mdp.petmed.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdp.petmed.Basket.BasketService;
import com.mdp.petmed.Order.OrderDTO;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final BasketService basketService;
    
    public UserController(UserService userService, BasketService basketService) {
        this.userService = userService;
        this.basketService = basketService;
    }

    @PostMapping("/signin")
    public UserDTO signIn(@RequestBody UserSignInDTO signInDTO){
        return new UserDTO(userService.signIn(signInDTO));
    }

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody UserDTO userDTO){
        UserModel user = userService.signUp(userDTO);
        basketService.create(user);
        return new UserDTO(user);
    }

    @PostMapping("/update")
    public UserDTO update(@RequestBody UserDTO userDTO){
        UserModel user = userService.update(userDTO);
        basketService.create(user);
        return new UserDTO(user);
    }

    @GetMapping("/getorders/{id}")
    public List<OrderDTO> getUserOrders(@PathVariable("id") Long id){
        return userService.getUserOrders(id).stream()
                .map(OrderDTO::new)
                .toList();
    }
}
