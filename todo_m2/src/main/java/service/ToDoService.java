package service;

import java.util.List;

import dto.ToDoDto;

// DAO의 CRUD 메소드를 호출

public interface ToDoService {
    List<ToDoDto> getList();

    ToDoDto getRow(String no);

    boolean insert(ToDoDto insertDto);

    boolean update(ToDoDto insertDto);

    boolean delete(String no);
}
