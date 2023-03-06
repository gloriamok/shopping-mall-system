<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<meta charset="UTF-8">
<html>
<head>
<title>Home</title>
</head>
<% request.setCharacterEncoding("UTF-8"); %>
<body>
<c:if test="${! empty authInfo}">
<P> <spring:message code="modifyuserinfo" /> </P>
<form:form action="submituserinfo" modelAttribute="userInfo">
	<p> <label> <spring:message code="name" />:<br>
	<form:input path="name" /> <form:errors path="name" /> </label> </p>
	<p> <label> <spring:message code="birthday" />:<br>
	<form:input path="birthday" placeholder="날짜 입력 (yyyymmdd)" /> 	<form:errors path="birthday" /> </label> </p>
	<p> <label> <spring:message code="email" />:<br>
	<form:input path="email" /> <form:errors path="email" /> </label> </p>
	<p> <label> <spring:message code="address" />:<br>
	<form:input path="address" /> <form:errors path="address" /> </label> </p>
	<p> <label> <spring:message code="phoneNum" />:<br>
	<form:input path="phoneNum" placeholder="전화번호 입력 (xxx-xxxx-xxxx)" /> <form:errors path="phoneNum" /> </label> </p>
	<button class="btn" type="submit"> <spring:message code="submit" />
	</button>
</form:form>
</c:if>
</body>
</html>