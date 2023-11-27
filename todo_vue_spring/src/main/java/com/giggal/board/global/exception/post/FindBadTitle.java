package com.giggal.board.global.exception.post;

public class FindBadTitle extends RuntimeException {
    public FindBadTitle(String title) {
        super("don`t write bad title" + title);
    }
}
