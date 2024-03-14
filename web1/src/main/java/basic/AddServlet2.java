package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2") // 별칭 중복불가
public class AddServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 사용자 입력값 가져오기
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        String op = req.getParameter("op");

        // 사칙연산 화면 출력
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        int result = 0;

        switch (op) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num1 != 0 && num2 != 0) {
                    result = num1 / num2;
                }
                break;
            default:
                break;
        }

        if (op.equals("/") && (num1 == 0 || num2 == 0)) {
            out.println("0으로는 나눌 수 없습니다.");
        } else {
            out.println(num1 + op + num2 + " = " + result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
