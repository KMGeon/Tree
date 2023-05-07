package com.giggal.board.global.exception.post;

public class NotFoundPostWithId extends RuntimeException {
    public NotFoundPostWithId(Long postId) {
        super("Not Found Post With" + postId);
    }
}
