package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReplyAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardDto replyDto = new BoardDto();
        replyDto.setName(req.getParameter("name"));
        replyDto.setTitle(req.getParameter("title"));
        replyDto.setContent(req.getParameter("content"));
        replyDto.setPassword(req.getParameter("password"));
        // 부모의 정보
        replyDto.setReRef(Integer.parseInt(req.getParameter("reRef")));
        replyDto.setReSeq(Integer.parseInt(req.getParameter("reSeq")));
        replyDto.setReLev(Integer.parseInt(req.getParameter("reLev")));

        BoardService service = new BoardServiceImpl();

        // 성공 시 리스트 보여주기 실패하면 댓글 작성화면으로 다시
        if (!service.reply(replyDto)) {
            path = "/qReplyView.do?bno=" + req.getParameter("bno");
        }

        return new ActionForward(path, true);
    }

}
