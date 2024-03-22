package action;

import javax.servlet.http.HttpServletRequest;

import dto.ToDoDto;
import service.ToDoService;
import service.ToDoServiceImpl;

public class ToDoReadAction implements Action {

    private String Path;

    public ToDoReadAction(String path) {
        Path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String no = req.getParameter("no");

        ToDoService service = new ToDoServiceImpl();
        ToDoDto todo = service.getRow(no);

        req.setAttribute("todo", todo);

        // req.setAttribute 니깐 false (.sendRedirect() 안 함)
        return new ActionForward(Path, false);
    }

}
