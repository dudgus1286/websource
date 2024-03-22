package service;

import java.util.List;

import dao.BookDao;
import dto.BookDto;

public class BookServiceImpl implements BookService {

    BookDao dao = new BookDao();

    @Override
    public BookDto read(int code) {
        return dao.getrow(code);
    }

    @Override
    public List<BookDto> listAll() {
        return dao.getList();
    }

    @Override
    public List<BookDto> searchlistAll(String criteria, String keyword) {
        return dao.searchList(criteria, keyword);
    }

    @Override
    public Boolean create(BookDto insertDto) {
        return dao.insert(insertDto) == 1;
    }

    @Override
    public Boolean update(BookDto updateDto) {
        return dao.update(updateDto) == 1;
    }

    @Override
    public Boolean delete(int code) {
        return dao.delete(code) == 1;
    }

}
