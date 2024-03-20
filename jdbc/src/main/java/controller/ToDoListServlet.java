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

@WebServlet("/list")
public class ToDoListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DB 연동 1) Dao 클래스의 함수를 쓸 수 있게 가져오기
        ToDoDao dao = new ToDoDao();
        // 2) Dto 리스트 생성해서 1의 dao 리스트 넣기
        List<ToDoDto> list = dao.getList();

        // 작업 결과를 scope(request)에 담기
        req.setAttribute("list", list);

        // 보낼 경로와 값 지정
        RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
