<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%@ include file="/include/header.jsp" %>

<h3 class="border-bottom mb-3">도서 입력</h3>
<form action='<c:url value="/create.do" />' method="post">
  <div class="mb-3">
    <label for="code" class="form-label">code</label>
    <input type="text" class="form-control" id="code" name="code">
  </div>
  <div class="mb-3">
    <label for="title" class="form-label">title</label>
    <input type="text" class="form-control" id="title" name="title">
  </div>
  <div class="mb-3">
    <label for="writer" class="form-label">writer</label>
    <input type="text" class="form-control" id="writer" name="writer">
  </div>
  <div class="mb-3">
    <label for="price" class="form-label">price</label>
    <input type="text" class="form-control" id="price" name="price">
  </div>
  <div class="mb-3">
    <label for="description" class="form-label">description</label>
    <textarea class="form-control" name="description" id="description" rows="3"></textarea>
  </div>
  <div class="mb-3">
  <button type="submit" class="btn btn-success">추가</button>
  <a href='<c:url value="/list.do" />' class="btn btn-primary">목록</a>
  </div>
</form>

<%@ include file="/include/section.jsp" %>
<script src='<c:url value="/js/create.js" />'></script>
<%@ include file="/include/footer.jsp" %>