package dev.test.aswemake.domain.entity.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    USER("ROLE_USER"),
    MARKET("ROLE_MARKET");

    private final String roleName;


    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

}
