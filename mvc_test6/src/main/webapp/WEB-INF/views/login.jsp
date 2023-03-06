<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 메시지 처리를 위한 spring:message 태그 사용을 위해 다음 taglib 설정을 추가 -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- form 태그라이브러리를 사용하기 위해서 다음 taglib 설정을 추가 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	
<meta charset="UTF-8">
<html>
<head>
<title>LogIn</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<P> <spring:message code="loginmsg" /> </P>
<form:form action="submit2" modelAttribute="userInfo">
	<p> <label> <spring:message code="userid" />:<br>
	<form:input path="userid" /> <form:errors path="userid" /> </label>
	<br> <spring:message code="rememberid" />
	<form:checkbox path="rememberid" /> </p>
	<p> <label> <spring:message code="pwd" />:<br>
	<form:password path="pwd" /> <form:errors path="pwd" /> </label> </p>
	<button class="btn" type="submit"> <spring:message code="submit" /> </button>
</form:form>
</body>
</html>