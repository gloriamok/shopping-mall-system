<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!-- 사용자 정의 태그 파일 포함 -->
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
	
<!-- 메시지 처리를 위한 spring:message 태그 사용을 위해 다음 taglib 설정을 추가 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<meta charset="UTF-8">	
<html>
<head>
	<title>Product2</title>
</head>
<% request.setCharacterEncoding("UTF-8"); %>
<body>

<div style="position: absolute; left: 30px; top: 50px; text-align: center">
	<img src="http://img.danawa.com/prod_img/500000/894/793/img/5793894_1.jpg" width="200" height="200">
	<p> ${queryResult} </p>
	<c:if test="${! empty authInfo}">
		<a href="./order4"> <spring:message code="order" /> </a> <br>
	</c:if>
</div>

<a href="./"> <spring:message code="back" /> </a>

</body>
</html>