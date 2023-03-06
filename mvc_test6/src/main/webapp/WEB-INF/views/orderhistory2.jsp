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
	<title>OrderHistory2</title>
</head>
<% request.setCharacterEncoding("UTF-8"); %>
<body>

<c:if test="${! empty authInfo}">
	<tf:formatDate value="${queryOrderDate1}" pattern="yyyy-MM-dd" />과
	<tf:formatDate value="${queryOrderDate2}" pattern="yyyy-MM-dd" />사이의
	${authInfo.userid}님의 구매이력 <br>
	<p> ${queryResult} </p>
	
	<a href="./searchbyperiod"> <spring:message code="back" /> </a>
</c:if>






</body>
</html>