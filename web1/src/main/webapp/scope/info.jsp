<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<% 
    request.setCharacterEncoding("utf-8");

    // id, name, age 가져오기
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String age = request.getParameter("age");

    // HttpServletRequest : 유효범위가 제한되어 있음
    // request.getParameter() : 사용자의 입력값을 가지고 올 때 범위 제한
    // form action 의 값으로 지정된 페이지까지만 가능

    // info.jsp 가 알고 있는 값(사용자 입력값, DB값 등)을 다른 페이지랑 공유
    // 1) get/post 방식으로 넘기기 (새 form 또는 a 태그에 값을 담아서 넘기기)
    // 2) scope 이용
        // (1) page : 현재 페이지
        // (2) request : HttpServletRequest 유효범위 (form action에서 명시한 범위까지) + .forward() 메소드도 같이 사용
        // (3) session : HttpSession 유효범위 (브라우저를 닫아서 세션이 사라지기 전까지 유지)
        // (4) application : 톰캣 서버의 범위(X)

        // scope용 메소드 : setAttribute(String "key", 값-종류상관없음) : Object, getAttribute("key")
        // 예) request.setAttribute(), session.getAttribute()
 %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%-- <h3>id: <%=id %></h3>
    <h3>name: <%=name %></h3>
    <h3>age: <%=age %></h3> --%>

    <%
    // request scope 사용 - 일반적으로 오류 남, forward() 사용
        request.setAttribute("id",id);
        request.setAttribute("name",name);
        request.setAttribute("age",age);

        // info.jsp 에서 부여된 request를 next.jsp 에 넘겨주는 것
        // info.jsp 에서 할 수 있는 작업들을 next.jsp에서도 할 수 있게 됨
        pageContext.forward("next.jsp");
        // 주소는 info.jsp 지만 보여지는 내용은 next.jsp임 (주소 != 내용)
        // forward()로 info.jsp까지만 영향을 줄 수 있는 request의 내용을 next.jsp에도 가져올 수 있었음
    
    // session scope 사용
        // session.setAttribute("id",id);
        // session.setAttribute("name",name);
        // session.setAttribute("age",age);

     %>

    <%-- <form action="next.jsp" method="post">
      <div>
        <label for="id">id</label>
        <input type="text" name="id" id="id" value="<%=id %>" readonly />
      </div>
      <div>
        <label for="name">name</label>
        <input type="text" name="name" id="name" value="<%=name %>" readonly/>
      </div>
      <div>
        <label for="age">age</label>
        <input type="text" name="age" id="age" value="<%=age%>" readonly/>
      </div>
      <div>
        <button type="submit">전송</button>
      </div>
    </form> --%>

    <%-- <h3>
    <a href="next.jsp?id=<%=id%>&name=<%=name%>&age=<%=age%>">다음 페이지</a>
    </h3> --%>

    <%-- <h3>
    <a href="next.jsp">다음 페이지</a>
    </h3> --%>
</body>
</html>