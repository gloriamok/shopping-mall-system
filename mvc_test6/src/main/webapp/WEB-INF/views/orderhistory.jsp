<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!-- 사용자 정의 태그 파일 포함 -->
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
	
<!-- 메시지 처리를 위한 spring:message 태그 사용을 위해 다음 taglib 설정을 추가 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- form 태그라이브러리를 사용하기 위해서 다음 taglib 설정을 추가 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<meta charset="UTF-8">	
<html>
<head>
	<title>OrderHistory</title>
</head>
<% request.setCharacterEncoding("UTF-8"); %>
<body>

<c:if test="${! empty authInfo}">
<P> ${authInfo.userid}님의 구매이력 </P>
<p> ${queryResult} </p>
<form:form action="${authInfo.userid}" modelAttribute="orderHistory">
	<label> 제품명으로 구매이력 조회 <form:input path="productName" placeholder="제품명을 입력하세요." /> </label>
	<button class="btn" type="submit"> <spring:message code="search" />
	</button>
</form:form>
<p> <a href="/finalexam/searchbyperiod"> 기간으로 구매이력 조회 </a> <p>
<a href="/finalexam"> <spring:message code="back" /> </a>
</c:if>


</body>
</html>