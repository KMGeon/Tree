package com.giggal.board.domain.role.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    CREW("ROLE_CREW"),
    TLeader("ROLE_TLeader"),
    PM("ROLE_PM"),
    ADMIN("ROLE_ADMIN");

    private final String roleName;


    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

}
