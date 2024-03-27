package service;

import java.util.List;

import dto.BoardDto;

public interface BoardService {
    public List<BoardDto> list();

    public boolean insert(BoardDto insertDto);

    public BoardDto read(int bno);

    public boolean update(BoardDto updateDto);

    public boolean delete(BoardDto deleteDto);

    public boolean reply(BoardDto replyDto);
}
