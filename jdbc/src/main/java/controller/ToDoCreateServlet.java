package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ToDoDao;
import dto.ToDoDto;

@WebServlet("/create")
public class ToDoCreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        // DB 작업
        // DB 연동 1) Dao 클래스의 함수를 쓸 수 있게 가져오기
        ToDoDao dao = new ToDoDao();

        ToDoDto insertDto = new ToDoDto();
        insertDto.setTitle(title);
        insertDto.setDescription(description);

        int result = dao.insert(insertDto);

        // 화면 이동(list.jsp)
        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
