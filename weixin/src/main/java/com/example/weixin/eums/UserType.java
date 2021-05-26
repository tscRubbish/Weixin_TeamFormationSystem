package com.example.weixin.eums;

public enum UserType {
    Admin(0),
    User(1),
    Manager(2);
    private Integer value=1;

    UserType(Integer value) {
        this.value = value;
    }


    public Integer getValue() {
        return value;
    }
}
