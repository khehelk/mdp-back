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

    // Метод для получения enum по строковому значению
    public static RoleEnum fromString(String stringValue) {
        for (RoleEnum myEnum : RoleEnum.values()) {
            if (myEnum.role.equals(stringValue)) {
                return myEnum;
            }
        }
        throw new IllegalArgumentException("No enum constant with string value: " + stringValue);
    }
}
