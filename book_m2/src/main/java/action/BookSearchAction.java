package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookSearchAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        BookService service = new BookServiceImpl();
        List<BookDto> list = service.searchlistAll(criteria, keyword);

        req.setAttribute("list", list);

        return new ActionForward(path, false);
    }

}
