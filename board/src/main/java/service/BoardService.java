package service;

import java.util.List;

import dto.BoardDto;

public interface BoardService {
    public List<BoardDto> list();

    public boolean insert(BoardDto insertDto);

    public BoardDto read(int bno);
}
