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
import action.ToDoListAction;
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
        ToDoDao dao = new ToDoDao();
        ToDoService service = new ToDoServiceImpl();
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
        // System.out.println("cmd " + cmd);

        if (cmd.equals("/list.do")) {
            action = new ToDoListAction("/view/list.jsp");
        } else if (cmd.equals("/read.do")) {
            String no = req.getParameter("no");

            // DB 작업
            ToDoDto todo = service.getRow(no);

            // todo 를 read.jsp 에 보여주기
            req.setAttribute("todo", todo);

            // RequestDispatcher rd = req.getRequestDispatcher("/view/read.jsp");
            // rd.forward(req, resp);
        } else if (cmd.equals("/modify.do")) {
            String no = req.getParameter("no");

            // DB 작업
            ToDoDto todo = service.getRow(no);

            // todo 를 modify.jsp 에 보여주기
            req.setAttribute("todo", todo);

            // RequestDispatcher rd = req.getRequestDispatcher("/view/modify.jsp");
            // rd.forward(req, resp);
        } else if (cmd.equals("/update.do")) {
            String no = req.getParameter("no");
            String description = req.getParameter("description");

            // checkbox, radio에 value 가 없는 경우 on 값을 가지고 오게 되기 때문에 값을 "true"로 설정해야 함
            String completed = req.getParameter("completed");

            // DB 작업
            ToDoDto dto = new ToDoDto();
            dto.setNo(Integer.parseInt(no));
            dto.setCompleted(Boolean.parseBoolean(completed));
            dto.setDescription(description);

            boolean result = service.update(dto);

            // 화면 이동(list)
            // resp.sendRedirect("list.do");
        } else if (cmd.equals("/delete.do")) {
            String no = req.getParameter("no");

            // DB 작업
            boolean result = service.delete(no);

            // 화면 이동(list)
            // resp.sendRedirect("list.do");
        } else if (cmd.equals("/create.do")) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            // DB 작업
            // DB 연동 1) Dao 클래스의 함수를 쓸 수 있게 가져오기
            ToDoDto insertDto = new ToDoDto();
            insertDto.setTitle(title);
            insertDto.setDescription(description);

            int result = dao.insert(insertDto);

            // 화면 이동(list.jsp)
            // resp.sendRedirect("list.do");

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
