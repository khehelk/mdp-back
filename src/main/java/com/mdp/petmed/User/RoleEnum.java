package com.mdp.petmed.User;

public enum RoleEnum {
    ADMIN ("Admin"),
    USER ("User");    

    private String role;

    RoleEnum(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
