package service;

import java.util.List;

import dto.BookDto;

public interface BookService {
    // DAO를 호출하는 인터페이스
    // CRUD - 조회, 검색, 삽입, 삭제, 수정
    BookDto read(int code);

    List<BookDto> listAll();

    List<BookDto> searchlistAll(String criteria, String keyword);

    Boolean create(BookDto insertDto);

    Boolean update(BookDto updateDto);

    Boolean delete(int code);
}
