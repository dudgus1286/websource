package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardSearchAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        SearchDto searchDto = new SearchDto();
        searchDto.setCriteria(req.getParameter("criteria"));
        searchDto.setKeyword(req.getParameter("keyword"));

        BoardService service = new BoardServiceImpl();
        List<BoardDto> list = service.search(searchDto);

        req.setAttribute("list", list);
        req.setAttribute("search", searchDto);
        return new ActionForward(path, false);
    }

}
