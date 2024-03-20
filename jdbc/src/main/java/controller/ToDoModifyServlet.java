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

@WebServlet("/modify")
public class ToDoModifyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 입력한 todo 가져오기
        req.setCharacterEncoding("utf-8");
        String no = req.getParameter("no");

        // DB 작업
        ToDoDao dao = new ToDoDao();
        ToDoDto todo = dao.getRow(no);

        // todo 를 modify.jsp 에 보여주기
        req.setAttribute("todo", todo);

        RequestDispatcher rd = req.getRequestDispatcher("/view/modify.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
