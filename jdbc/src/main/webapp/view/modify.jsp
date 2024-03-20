<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file = "../include/header.jsp" %>
<%@ page import = "dto.ToDoDto"%>

<%
  // request 에 값이 담겨져 있어서 todo.get~()를 ${todo.변수}로 표현가능
  // ${} 로 표현하면 request.getAttribute("todo") 필요 없음
  // ToDoDto todo = (ToDoDto) request.getAttribute("todo");
 %>

 <h1 class="mt-5">Todo Modify</h1>
 <form action="${pageContext.request.contextPath}/update" method="post">
 <div class="mb-3">
    <label for="title" class="form-label">title</label>
    <%-- <input type="text" class="form-control" id="title" placeholder="title" name="title" value="<%=todo.getTitle()%>"> --%>
    <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${todo.title}" readonly>
    <%-- readPro.jsp에서 request.setAttribute() 안에 쓴 키 이름이 todo라서 따라 씀 --%>
  </div>
  <div class="mb-3">
    <label for="createdAt" class="form-label">createdAt</label>
    <%-- <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="<%=todo.getCreatedAt()%>"> --%>
    <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="${todo.createdAt}" readonly>
  </div>
  <div class="mb-3">
    <label for="completed" class="form-check-label">completed</label>
    <%-- completed 가 true면 check 표시 --%>
    <input type="checkbox" name="completed" id="completed" class="form-check-input" value="true" <c:out value="${todo.completed?'checked':''}"/>>
  </div>

  <div class="mb-3">
    <label for="description" class="form-label">description</label>
    <%-- <textarea class ="form-control" id="description" rows="3" name="description"><%=todo.getDescription()%></textarea> --%>
    <textarea class ="form-control" id="description" rows="3" name="description">${todo.description}</textarea>
  </div>
  <div>
    <button class="btn btn-primary" type="submit">수정</button>
    <a href='<c:url value="/delete?no=${todo.no}" />' class="btn btn-danger">삭제</a>
    <a href='<c:url value="/list" />' class="btn btn-success">목록</a>
  </div>
  <%-- 화면에 표시되지 않는 값 추가 --%>
  <input type="hidden" name="no" value="${todo.no}">
 </form>
<%@ include file = "../include/footer.jsp" %>