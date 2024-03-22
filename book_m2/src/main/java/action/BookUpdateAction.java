package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookUpdateAction implements Action {

    private String path;

    public BookUpdateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        int code = Integer.parseInt(req.getParameter("code"));
        int price = Integer.parseInt(req.getParameter("price"));
        BookDto updateDto = new BookDto();
        updateDto.setCode(code);
        updateDto.setPrice(price);

        BookService service = new BookServiceImpl();
        boolean result = service.update(updateDto);

        if (result) {
            // read.do 에 수정 내용 보여주기
            // req.setAttribute("code", code);
            path += "?code=" + updateDto.getCode();
        } else {
            path = "/view/modify.jsp";
        }
        return new ActionForward(path, true);
    }

}
