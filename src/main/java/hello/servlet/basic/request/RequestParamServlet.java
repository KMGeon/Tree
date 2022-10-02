package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/*
 *1.파라미터 전송
 * username=hello&age=20
 */

@WebServlet(name="requestParamServlet" , urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회 시작");
        req.getParameterNames().asIterator().forEachRemaining(paramName-> System.out.println(paramName+":"+req.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 끝");
        Enumeration<String> parameterNames = req.getParameterNames(); //모든 요청 파라미터 다 꺼냄


        System.out.println("단일 파라미터 조회");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("age = " + username);


        System.out.println("이름이 같은 복수 파라미터 조회");
        String[]name= req.getParameterValues("username");
        for (String names : name) {
            System.out.println("names = " + names);
            System.out.println("age = " + age);

        }

        resp.getWriter().write("ok");
    }
}
