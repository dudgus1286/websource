<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<% 
    request.setCharacterEncoding("utf-8");

    // id, name, age 가져오기
    // info.jsp a 태그가 보낸 id, name, age 가져오기
    // String id = request.getParameter("id");
    // String name = request.getParameter("name");
    // String age = request.getParameter("age");

    // request.getAttribute()
    // 값이 Ovject 이기 때문에 String 으로 형변환해야 함
    String id = (String) request.getAttribute("id");
    String name = (String) request.getAttribute("name");
    String age = (String) request.getAttribute("age");

    // session.getAttribute()
    // String id = (String) session.getAttribute("id");
    // String name = (String) session.getAttribute("name");
    // String age = (String) session.getAttribute("age");
 %>

 <!DOCTYPE html>
 <html lang="en">
 <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
 </head>
 <body>
 <h3>next.jsp</h3>
    <h3>id: <%=id %></h3>
    <h3>name: <%=name %></h3>
    <h3>age: <%=age %></h3>

    <h3>
    <a href="next2.jsp">다음 페이지</a>
    </h3>
 </body>
 </html>