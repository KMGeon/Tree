package com.giggal.board.global.exception.member;

public class NotFoundMemberEmail extends RuntimeException {
    public NotFoundMemberEmail(String email) {
        super("User email is not found : " + email);
    }
}
