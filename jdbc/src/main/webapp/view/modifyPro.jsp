<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%@ page import = "dao.ToDoDao"%>
<%@ page import = "dto.ToDoDto"%>
<%
    // 사용자가 입력한 todo 가져오기
    request.setCharacterEncoding("utf-8");
    String no = request.getParameter("no") ;

    // DB 작업
    ToDoDao dao = new ToDoDao();
    ToDoDto todo = dao.getRow(no);

    // todo 를 modify.jsp 에 보여주기
    request.setAttribute("todo",todo);

    // 화면 이동(modify.jsp)
    pageContext.forward("modify.jsp");
 %>