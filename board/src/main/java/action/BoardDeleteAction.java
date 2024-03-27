package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardDto deleteDto = new BoardDto();
        deleteDto.setBno(Integer.parseInt(req.getParameter("bno")));
        deleteDto.setPassword(req.getParameter("password"));

        BoardService service = new BoardServiceImpl();
        if (!service.delete(deleteDto)) {
            path = "/view/qna_board_pwdCheck.jsp?bno=" + deleteDto.getBno();
        }
        return new ActionForward(path, true);
    }

}
