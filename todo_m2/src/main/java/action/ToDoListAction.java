package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ToDoDto;
import service.ToDoService;
import service.ToDoServiceImpl;

public class ToDoListAction implements Action {

    private String path;

    public ToDoListAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        ToDoService service = new ToDoServiceImpl();
        List<ToDoDto> list = service.getList();
        req.setAttribute("list", list);
        return new ActionForward(path, false);
    }

}
