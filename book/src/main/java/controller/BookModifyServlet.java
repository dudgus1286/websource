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

@WebServlet("/modify")
public class BookModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookDto dto = new BookDto();
        // Integer.parseInt("") : 공백 입력 시 => NumberFormatException 발생
        dto.setCode(Integer.parseInt(req.getParameter("code")));
        dto.setPrice(Integer.parseInt(req.getParameter("price")));

        BookDao dao = new BookDao();
        int result = dao.update(dto);

        // 성공 시 list 페이지, 실패 시 modify.jsp 페이지로
        if (result > 0) {
            resp.sendRedirect("list");
        } else {
            resp.sendRedirect("/view/modify.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
