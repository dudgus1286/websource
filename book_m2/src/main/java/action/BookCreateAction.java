package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookCreateAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 도서입력 화면에서 넘어오는 데이터 가져오기
        int code = Integer.parseInt(req.getParameter("code"));
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        int price = Integer.parseInt(req.getParameter("price"));
        String description = req.getParameter("description");

        BookDto insertDto = new BookDto();
        insertDto.setCode(code);
        insertDto.setTitle(title);
        insertDto.setWriter(writer);
        insertDto.setPrice(price);
        insertDto.setDescription(description);

        // 서비스 호출
        BookService service = new BookServiceImpl();
        boolean result = service.create(insertDto);

        return new ActionForward(path, true);
    }

}
