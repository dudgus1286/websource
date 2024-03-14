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
    int num1 = Integer.parseInt(request.getParameter("num1"));
    int num2 = Integer.parseInt(request.getParameter("num2"));
    String op = request.getParameter("op");

    double result = 0;
    switch(op){
        case "+":
        result = num1 + num2;
        break;
        case "-":
        result = num1 - num2;
        break;
        case "*":
        result = num1 * num2;
        break;
        case "/":
        if (!(num1 == 0 || num2 == 0))
        result = num1 / (double)num2;
        break;
        default:
        break;
    }
     %>

     <h1><%=num1%> <%=op%> <%=num2%> = <%
     if((num1 == 0 || num2 == 0) && op.equals("/"))
     {out.print("0으로 나눌 수 없습니다");}
     else {out.print(result);}
     %></h1>
</body>
</html>