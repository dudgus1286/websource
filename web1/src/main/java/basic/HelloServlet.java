package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HttpServletRequest - 사용자의 요청을 가져오는 객체
// HttpServletResponse - 사용자한테 응답할 때 사용하는 객체

@WebServlet("/hello") // Servlet 별칭 (임의지정 가능)
public class HelloServlet extends HttpServlet {
    // 오버라이딩
    // form[method="get"]의 경우
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // post 방식의 한글은 깨짐 - 가져오기 전에 한글 표시할 수 있도록 처리해야 함
        // 한글 인코딩 처리
        req.setCharacterEncoding("utf-8");

        // 가져오는 모든 데이터는 String
        // req.getParameter("form 요소(name)명")
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        // value가 여러 개인 checkbox 값 가져오기
        String[] dogs = req.getParameterValues("dog");

        // 응답할 페이지 설정
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.print("<ul>");
        out.print("<li>id : " + id + "</li>");
        out.print("<li>name : " + name + "</li>");
        for (String dog : dogs) {
            out.print("<li>dog : " + dog + "</li>");
        }
        out.print("</ul>");
    }

    // form[method="post"]의 경우
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
