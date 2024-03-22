package service;

import java.util.List;

import dao.ToDoDao;
import dto.ToDoDto;

public class ToDoServiceImpl implements ToDoService {

    ToDoDao dao = new ToDoDao();

    @Override
    public List<ToDoDto> getList() {
        return dao.getList();
    }

    @Override
    public ToDoDto getRow(String no) {
        return dao.getRow(no);
    }

    @Override
    public boolean insert(ToDoDto insertDto) {
        // 결과가 1이면 true, 아니면 false
        return dao.insert(insertDto) == 1;
        // 식을 풀어서 쓰면:
        // boolean flag = dao.insert(insertDto) == 1 ? true : false
        // return flag;

    }

    @Override
    public boolean update(ToDoDto insertDto) {
        return dao.update(insertDto) == 1;
    }

    @Override
    public boolean delete(String no) {
        return dao.delete(no) == 1;
    }

}
