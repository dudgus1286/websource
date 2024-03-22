package action;

import javax.servlet.http.HttpServletRequest;

import service.ToDoService;
import service.ToDoServiceImpl;

public class ToDoDeleteAction implements Action {
    private String path;

    public ToDoDeleteAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String no = req.getParameter("no");
        ToDoService service = new ToDoServiceImpl();
        boolean result = service.delete(no);

        return new ActionForward(path, true);
    }

}
