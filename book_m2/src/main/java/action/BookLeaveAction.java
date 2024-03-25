package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookLeaveAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        MemberDto dto = new MemberDto();
        dto.setUserid(req.getParameter("userid"));
        dto.setPassword(req.getParameter("password"));

        BookService service = new BookServiceImpl();
        if (service.leave(dto)) {
            session.invalidate();
        } else {
            path = "/view/leave.jsp";
        }
        return new ActionForward(path, false);
    }

}
