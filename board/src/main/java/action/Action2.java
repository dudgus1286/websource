package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action2 {
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
