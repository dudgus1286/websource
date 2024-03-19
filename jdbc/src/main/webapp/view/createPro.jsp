<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import = "dao.ToDoDao"%>
<%@ page import = "dto.ToDoDto"%>
<%
    // 사용자가 입력한 todo 가져오기
    request.setCharacterEncoding("utf-8");
    String title = request.getParameter("title") ;
    String description = request.getParameter("description") ;

    // DB 작업
    // DB 연동 1) Dao 클래스의 함수를 쓸 수 있게 가져오기
    ToDoDao dao = new ToDoDao();

    ToDoDto insertDto = new ToDoDto();
    insertDto.setTitle(title);
    insertDto.setDescription(description);

    int result = dao.insert(insertDto);

    // 화면 이동(list.jsp)
    response.sendRedirect("list.jsp");
 %>