<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    // info2.jsp에 부여된 request를 forward.jsp에 넘겨줌
    // info2.jsp에서 할 수 있는 작업들을 forward.jsp에서도 할 수 있게 됨
    pageContext.forward("forward.jsp");
 %>