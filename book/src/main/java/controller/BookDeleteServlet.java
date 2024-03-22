package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dto.BookDto;

@WebServlet("/delete")
public class BookDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // delete.jsp 에서 넘긴 값 가져오기
        int code = Integer.parseInt(req.getParameter("code"));

        BookDao dao = new BookDao();
        int result = dao.delete(code);

        // 성공 시 list 페이지, 실패 시 delete.jsp 페이지로
        if (result > 0) {
            resp.sendRedirect("list");
        } else {
            resp.sendRedirect("/view/delete.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
