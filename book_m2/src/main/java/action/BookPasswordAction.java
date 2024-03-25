package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.ChangeDto;
import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookPasswordAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // pwdChange.jsp 에서 넘긴 값 가져오기
        String password = req.getParameter("password");
        String newPassword = req.getParameter("new-password");
        String confirmPassword = req.getParameter("confirm-password");

        // 아이디와 현재 비밀번호 확인(서비스 메소드 호출)
        HttpSession session = req.getSession();
        MemberDto loginDto = (MemberDto) session.getAttribute("loginDto");

        ChangeDto changeDto = new ChangeDto();
        changeDto.setUserid(loginDto.getUserid());
        changeDto.setNewPassword(newPassword);
        changeDto.setConfirmPassword(confirmPassword);

        if (changeDto.newPasswordEqualsConfirmPassword()) {
            // 세션에 담긴 아이디와 사용자가 입력한 비밀번호를 가지고 있는 객체
            MemberDto input = new MemberDto();
            input.setUserid(loginDto.getUserid());
            input.setPassword(password);

            BookService service = new BookServiceImpl();
            if (service.login(input) == null) { // 현재 비밀번호가 틀린 경우
                path = "/view/pwdChange.jsp";
            } else {
                // 사용자가 존재한다면 비밀번호 변경 서비스 메소드 호출

                if (service.change(changeDto)) {
                    // 변경 완료되면 세션 제거 후 로그인 페이지로 이동
                    session.invalidate();

                } else {
                    // false 면 pwdChange.jsp 로 이동
                    path = "/view/pwdChange.jsp";

                }

            }

        } else {
            path = "/view/pwdChange.jsp";
        }
        return new ActionForward(path, true);
    }

}
