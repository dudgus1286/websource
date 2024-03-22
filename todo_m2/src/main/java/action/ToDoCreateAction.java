package action;

import javax.servlet.http.HttpServletRequest;

import dto.ToDoDto;
import service.ToDoService;
import service.ToDoServiceImpl;

public class ToDoCreateAction implements Action {
    private String path;

    public ToDoCreateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        ToDoDto insertdto = new ToDoDto();
        insertdto.setTitle(title);
        insertdto.setDescription(description);

        ToDoService service = new ToDoServiceImpl();
        boolean result = service.insert(insertdto);

        return new ActionForward(path, true);
    }

}
