package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.ToDoCreateAction;
import action.ToDoDeleteAction;
import action.ToDoListAction;
import action.ToDoReadAction;
import action.ToDoUpdateAction;
import dao.ToDoDao;
import dto.ToDoDto;
import service.ToDoService;
import service.ToDoServiceImpl;

// 여러 기능의 서블렛을 통합함
// 보내는 곳이 어디든 뒤의 글자로 파악해서 어떤 요청을 하는지 확인
// 예: insert.do / update.do / delete.do / get.do
@WebServlet("*.do")
public class ToDoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Action action = null;

        // 경로에서 요청 찾기
        // http://localhost:8080/list.do 접속 시 output 에
        String requestUri = req.getRequestURI(); // /list.do (8080 이후의 값, ROOT로 떼어내지 않았다면 /todo_m2/list.do)
        String contextPath = req.getContextPath(); // (없음) (프로젝트명, ROOT로 떼어내지 않았다면 /todo_m2)
        String cmd = requestUri.substring(contextPath.length());
        // requestUri에서 contextPath가 있는 위치까지 뺀 문자열 데이터 출력
        // requestUri - contextPath = cmd
        // cmd를 통해 어디에서 왔는지 확인 가능

        // System.out.println("requestUri " + requestUri);
        // System.out.println("contextPath " + contextPath);
        System.out.println("cmd " + cmd);

        if (cmd.equals("/list.do")) {
            action = new ToDoListAction("/view/list.jsp");
        } else if (cmd.equals("/read.do")) {
            action = new ToDoReadAction("/view/read.jsp");
        } else if (cmd.equals("/modify.do")) {
            // read.do와 경로 빼고 다 같아서 같은 클래스 사용가능
            action = new ToDoReadAction("/view/modify.jsp");
        } else if (cmd.equals("/update.do")) {
            action = new ToDoUpdateAction("/list.do");
        } else if (cmd.equals("/delete.do")) {
            action = new ToDoDeleteAction("/list.do");
        } else if (cmd.equals("/create.do")) {
            action = new ToDoCreateAction("/list.do");
        }

        ActionForward af = null;
        try {
            af = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (af.isRedirect()) {
            resp.sendRedirect(af.getPath());
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(af.getPath());
            rd.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
