<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%@ page import = "dao.ToDoDao"%>
<%@ page import = "dto.ToDoDto"%>
<%
    request.setCharacterEncoding("utf-8");
    // no 만 가져오기
    String no = request.getParameter("no") ;

    // DB 작업
    ToDoDao dao = new ToDoDao();

    int result = dao.delete(no);

    // 화면 이동(list.jsp)
    response.sendRedirect("list.jsp");
 %>