package com.dev.wanted.domain.role.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String roleName;


    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

}
