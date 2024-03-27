package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardModifyAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 수정작업
        BoardDto dto = new BoardDto();
        // bno를 폼으로 보내지 않으면 뜨는 오류
        // NumberFormatException: Cannot parse null string
        dto.setBno(Integer.parseInt(req.getParameter("bno")));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        dto.setPassword(req.getParameter("password"));

        BoardService service = new BoardServiceImpl();
        if (!service.update(dto)) {
            // 수정 실패 시 수정화면으로 되돌아가기
            path = "/qModify.do?bno=" + dto.getBno();
        } else {
            // /qRead.do?bno=숫자
            path += "?bno=" + dto.getBno();
        }

        return new ActionForward(path, true);
    }

}
