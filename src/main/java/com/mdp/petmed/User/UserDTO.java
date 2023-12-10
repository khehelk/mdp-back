package com.mdp.petmed.User;

import java.nio.charset.StandardCharsets;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String photo;

    public UserDTO(UserModel user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.photo = new String(user.getPhoto(), StandardCharsets.UTF_8);
    }
}
