package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookListAction implements Action {
    private String path;

    public BookListAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // Service listAll 호출
        BookService service = new BookServiceImpl();

        // 결과 request에 담기
        List<BookDto> list = service.listAll();
        req.setAttribute("list", list);

        return new ActionForward(path, false);
    }

}
