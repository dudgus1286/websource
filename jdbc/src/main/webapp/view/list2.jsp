<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "dao.ToDoDao"%>
<%@ page import = "dto.ToDoDto"%>
<%@ page import = "java.util.List"%>
<%@ include file = "../include/header.jsp" %>

<%
    // DB 연동 1) Dao 클래스의 함수를 쓸 수 있게 가져오기
    ToDoDao dao = new ToDoDao();
    // 2) Dto 리스트 생성해서 1의 dao 리스트 넣기
    List<ToDoDto> list = dao.getList();
 %>

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
  <% for(ToDoDto dto:list){ %>
    <tr>
      <th scope="row"><%=dto.getNo()%></th>
      <td><a href="readPro.jsp?no=<%=dto.getNo()%>" class="text-decoration-none text-reset"><%=dto.getTitle()%></a></td>
      <td><%=dto.getCreatedAt()%></td>
      <%-- <td><%=dto.isCompleted()%></td> --%>
      <td>
        <%
        out.print("<input type='checkbox' name='completed' id='completed' class='form-check-input' disabled ");
        if (dto.isCompleted()) out.print("checked >");
        else out.print(">");
         %>
      </td>
    </tr>
  <% }%>
  </tbody>
</table>

<%@ include file = "../include/footer.jsp" %>