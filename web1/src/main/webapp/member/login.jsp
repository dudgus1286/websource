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
    <form action="/login" method="post">
    <div>
        <label for="userId">아이디</label>
        <input type="text" id="userId" name="userId"></input>
    </div>
    <div>
        <label for="password">비밀번호</label>
        <input type="text" id="password" name="password"></input>
    </div>
    <div>
        <label for="name">이름</label>
        <input type="text" id="name" name="name"></input>
    </div>
    <div>
        <button type="submit">전송</button>
    </div>
    </form>
</body>
</html>
