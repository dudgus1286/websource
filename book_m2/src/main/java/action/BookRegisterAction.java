package action;

import java.lang.reflect.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookRegisterAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // register.jsp 넘긴 값 가져오기
        req.setCharacterEncoding("utf-8");
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        MemberDto dto = new MemberDto(userid, password, name, email);

        // 서비스 호출
        BookService service = new BookServiceImpl();

        // 결과에 따라 성공시 login.jsp, 실패 시 register.jsp
        if (!service.register(dto)) {
            path = "/view/register.jsp";
        }
        return new ActionForward(path, true);
    }
}
