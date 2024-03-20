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

@WebServlet("/update")
public class ToDoUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 입력한 todo 가져오기
        req.setCharacterEncoding("utf-8");
        String no = req.getParameter("no");
        String description = req.getParameter("description");

        // checkbox, radio에 value 가 없는 경우 on 값을 가지고 오게 되기 때문에 값을 "true"로 설정해야 함
        String completed = req.getParameter("completed");

        // DB 작업
        ToDoDao dao = new ToDoDao();

        ToDoDto dto = new ToDoDto();
        dto.setNo(Integer.parseInt(no));
        dto.setCompleted(Boolean.parseBoolean(completed));
        dto.setDescription(description);

        int result = dao.update(dto);

        // 화면 이동(list)
        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
