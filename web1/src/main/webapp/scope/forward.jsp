<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
// ScopeServlet(info2.jsp)에서 할 수 있는 작업들을 forward.jsp에서도 할 수 있다
    // request.setCharacterEncoding("utf-8");
    String id = request.getParameter("id");
 %>

 <h3>forward.jsp 페이지입니다.</h3>
 <h4>id : <%=id %></h4>