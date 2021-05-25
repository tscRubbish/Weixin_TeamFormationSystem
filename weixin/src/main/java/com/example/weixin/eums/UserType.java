package com.example.weixin.eums;

public enum UserType {
    Admin(0),
    User(1),
    Manager(2);
    private Integer value;

    UserType(Integer value) {
        this.value = value;
    }


    public Integer getValue() {
        return value;
    }
}
