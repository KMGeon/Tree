package com.giggal.board.global.exception.member.extend;

import com.giggal.board.global.exception.member.MemberException;

public class EmailDuplication extends MemberException {

    public EmailDuplication(String message) {
        super("User email is already existed:" + message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
