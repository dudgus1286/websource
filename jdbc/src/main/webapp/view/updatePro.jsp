<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%@ page import = "dao.ToDoDao"%>
<%@ page import = "dto.ToDoDto"%>
<%
    // 사용자가 입력한 todo 가져오기
    request.setCharacterEncoding("utf-8");
    String no = request.getParameter("no") ;
    String description = request.getParameter("description") ;

    // checkbox, radio에 value 가 없는 경우 on 값을 가지고 오게 되기 때문에 값을 "true"로 설정해야 함
    String completed = request.getParameter("completed") ;

    // DB 작업
    ToDoDao dao = new ToDoDao();

    ToDoDto dto = new ToDoDto();
    dto.setNo(Integer.parseInt(no));
    dto.setCompleted(Boolean.parseBoolean(completed));
    dto.setDescription(description);

    int result = dao.update(dto);

    // 화면 이동(read.jsp)
    response.sendRedirect("list.jsp");
 %>