package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookLoginAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 아이디, 패스워드 가져오기
        MemberDto dto = new MemberDto();
        dto.setUserid(req.getParameter("userid"));
        dto.setPassword(req.getParameter("password"));

        // 서비스 호출
        BookService service = new BookServiceImpl();
        MemberDto loginDto = service.login(dto);

        // true일 경우 session 에 저장
        if (loginDto != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loginDto", loginDto);
        } else {
            path = "/view/login.jsp";
        }
        return new ActionForward(path, true);
    }

}
