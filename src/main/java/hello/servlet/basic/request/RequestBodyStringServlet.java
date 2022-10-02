package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyStringServlet  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        inputstrem으로 데이터를 읽을 수 있다.
        ServletInputStream inpuStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inpuStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = " + messageBody);
        resp.getWriter().write("ok");
    }
}
