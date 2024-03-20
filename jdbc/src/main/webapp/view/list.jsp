<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "../include/header.jsp" %>

 <h1 class="mt-5">Todo List</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성일</th>
      <th scope="col">완료여부</th>
    </tr>
  </thead>
  <tbody>
  <%-- 3) for문으로 dto리스트의 내용을 출력 --%>
  <c:forEach var="dto" items="${list}">
  <%-- items 안에 setAttribute()에 담아둔 변수명 --%>
    <tr>
      <th scope="row">${dto.no}</th>
      <td><a href='<c:url value="/read?no=${dto.no}" />' class="text-decoration-none text-reset">${dto.title}</a></td>
      <td>${dto.createdAt}</td>
      <%-- <td><%=dto.isCompleted()%></td> --%>
      <td>
        <input type="checkbox" name="completed" id="completed" class="form-check-input" value="true" <c:out value="${dto.completed?'checked':''}"/>>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<%@ include file = "../include/footer.jsp" %>