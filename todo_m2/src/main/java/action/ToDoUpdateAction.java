package action;

import javax.servlet.http.HttpServletRequest;

import dto.ToDoDto;
import service.ToDoService;
import service.ToDoServiceImpl;

public class ToDoUpdateAction implements Action {
    private String path;

    public ToDoUpdateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String no = req.getParameter("no");
        String description = req.getParameter("description");
        String completed = req.getParameter("completed");

        ToDoDto dto = new ToDoDto();
        dto.setNo(Integer.parseInt(no));
        dto.setCompleted(Boolean.parseBoolean(completed));
        dto.setDescription(description);

        ToDoService service = new ToDoServiceImpl();
        boolean result = service.update(dto);

        return new ActionForward(path, true);
    }

}
