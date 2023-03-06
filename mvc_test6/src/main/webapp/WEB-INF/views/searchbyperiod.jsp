<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
	
<!-- 메시지 처리를 위한 spring:message 태그 사용을 위해 다음 taglib 설정을 추가 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- form 태그라이브러리를 사용하기 위해서 다음 taglib 설정을 추가 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<meta charset="UTF-8">	
<html>
<head>
	<title>SearchByperiod</title>
</head>
<% request.setCharacterEncoding("UTF-8"); %>
<body>

<c:if test="${! empty authInfo}">

<form:form action="orderhistory2" modelAttribute="orderPeriod">
	<p> 기간으로 구매이력 조회 </p>
	<p> <label> <spring:message code="orderDate1" /> <form:input path="orderDate1" placeholder="날짜 입력 (yyyymmdd)" /> <form:errors path="orderDate1" /> </label> </p>
	<p> ~ <label> <spring:message code="orderDate2" /> <form:input path="orderDate2" placeholder="날짜 입력 (yyyymmdd)" /> <form:errors path="orderDate2" /> </label> </p>
	<button class="btn" type="submit"> <spring:message code="search" />
	</button>
</form:form>

<a href="./"> <spring:message code="back" /> </a>
</c:if>

</body>
</html>