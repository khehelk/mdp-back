package com.mdp.petmed.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.mdp.petmed.Order.OrderModel;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel update(UserDTO user){
        UserModel userUpdate = getUserById(user.getId());
            userUpdate.setName(user.getName());
            userUpdate.setSurname(user.getSurname());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setPhoto(user.getPhoto().getBytes(StandardCharsets.UTF_8));
        return userRepository.save(userUpdate);
    }

    @Transactional
    public UserModel signIn(UserSignInDTO userSignInDTO){
        UserModel user = userRepository.findByEmail(userSignInDTO.getEmail());
        if(Objects.equals(user.getPassword(), userSignInDTO.getPassword())){
            return user;
        }
        return null;
    }

    @Transactional
    public UserModel signUp(UserDTO user){
        UserModel userCreate = new UserModel(
            user.getName(), 
            user.getSurname(), 
            user.getEmail(), 
            user.getPassword(), 
            user.getPhoto().getBytes(StandardCharsets.UTF_8), 
            RoleEnum.USER);
        return userRepository.save(userCreate);
    }
    

    @Transactional
    public List<OrderModel> getUserOrders(Long id){
        UserModel user = userRepository.getReferenceById(id);
        return user.getOrders();
    }

    @Transactional
    public UserModel getUserById(Long id){
        return userRepository.getReferenceById(id);
    }
}
