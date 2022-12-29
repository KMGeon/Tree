package kr.or.funding.board.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}