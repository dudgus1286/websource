package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookLogoutAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();
        session.invalidate();
        // if (session.getAttribute("loginDto") != null) {
        // session.removeAttribute("loginDto");
        // }

        return new ActionForward(path, true);
    }

}
