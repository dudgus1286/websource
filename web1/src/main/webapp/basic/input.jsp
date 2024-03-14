<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- JSP 주석

    자바코드는 <% %> 안에 작성한다(위치는 상관없음)
    자바코드 화면출력 <%=자바코드%>
    자바코드는 브라우저가 해석할 수 없음 / 톰캣이 해석해서 브라우저에 출력
    자바코드 위치는 어디든 상관없음(소스코드에서 보이는 위치 차이 정도)
    // for(int i = 1; i < 11 ; i++){
    //     out.print(i);
    // }
 --%>
 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%
    // JSP 내장객체
    // 1) HttpServletRequest - request
    // 2) HttpServletResponse - response
    // 3) JspWriter - out
    request.setCharacterEncoding("utf-8");

    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String[] dogs = request.getParameterValues("dog");

    // 응답할 페이지 설정
    // res.setContentType("text/html;charset=utf-8"); <= JSP 문서 상단에 이미 선언되어 있음
    // PrintWriter out = res.getWriter(); <= 이미 내장객체
  %>
    <ul>
    <li>id : <%=id%></li>
    <li>name : <%=name%></li>
    <li>name : <% out.print(name); %></li>
    <% 
    for (String dog : dogs) {
            out.print("<li>dog : " + dog + "</li>");
        }
     %>
    </ul>
</body>
</html>