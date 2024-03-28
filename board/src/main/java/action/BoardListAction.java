package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.PageDto;
import dto.SearchDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardListAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 검색에 쓰이는 정보를 가져와서 SearchDto 에 담기
        int page = Integer.parseInt(req.getParameter("page"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        SearchDto searchDto = new SearchDto(page, amount);
        searchDto.setCriteria(criteria);
        searchDto.setKeyword(keyword);

        // 서비스 호출
        BoardService service = new BoardServiceImpl();
        // SearchDto 와 조회하는 데이터의 갯수를 가져와서 PageDto 에 담기
        // PageDto는 한 페이지에 담기는 데이터 개수에 따라 필요한
        // 전체 페이지 장수, 현재 페이지에서 보여지는 인덱스 버튼 수 확인
        PageDto pageDto = new PageDto(searchDto, service.getTotalRows(criteria, keyword));
        // 조회하려는 리스트 데이터 담기
        List<BoardDto> list = service.list(searchDto);

        // 리스트 데이터와 인덱스 정보를 다음 페이지로 넘기기
        req.setAttribute("list", list);
        // req.setAttribute("searchDto", searchDto);
        req.setAttribute("pageDto", pageDto); // searchDto 포함됨

        return new ActionForward(path, false);
    }

}
