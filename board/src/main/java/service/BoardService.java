package service;

import java.util.List;

import dto.BoardDto;
import dto.SearchDto;

public interface BoardService {
    public List<BoardDto> list(SearchDto searchDto);

    public boolean insert(BoardDto insertDto);

    public BoardDto read(int bno);

    public boolean update(BoardDto updateDto);

    public boolean delete(BoardDto deleteDto);

    public boolean reply(BoardDto replyDto);

    boolean updateCount(int bno);

    List<BoardDto> search(SearchDto searchDto);

    int getTotalRows(String criteria, String keyword);
}
