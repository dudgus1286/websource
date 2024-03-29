<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
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
            // sendRedirect("경로") : 페이지 이동
    // 3) JspWriter - out
    // 4) PageContext - pageContext : jsp 페이지에 대한 정보를 저장하고 있는 객체
            // 1) forward("경로") : 페이지 이동(주소 유지)
            // 2) include("포함할 페이지 경로") : ex) 디자인 템플릿 구성 시 사용
    // 5) HttpSession - session
    // 세션 : 특정 서버와 연결된 상태
    // https or http 프로토콜의 특징인
    // - 무상태(stateless) : 클라이언트의 상태를 저장하지 않음 (<=> 상태stateful 의 반대)
    // 그러나 상태 저장이 필요한 경우?
        // 1) 서버 측 - 세션으로 저장
    //같은 컴퓨터로 접속하더라도 각 브라우저, (크롬)브라우저의 로그인한 계정별로 다른 값을 서버에서 지급
    // 연결된 브라우저 창을 모두 닫으면 세션도 날라감
        // 2) 클라이언트 측 - 쿠키 / 브라우저 저장
 %>
 <h2>세션 테스트</h2>
 <ul>
    <li>isNew() : <%=session.isNew() %></li>
    <li>생성시간 : <%=session.getCreationTime() %></li>
    <li>최종접속시간 : <%=session.getLastAccessedTime() %></li>
    <li>세션ID : <%=session.getId() %></li>
 </ul>
</body>
</html>