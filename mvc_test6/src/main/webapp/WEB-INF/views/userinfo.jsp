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
	<title>UserInfo</title>
</head>
<% request.setCharacterEncoding("UTF-8"); %>
<body>

<c:if test="${! empty authInfo}">
<P> ${authInfo.userid}님 정보 </P>
<p> <spring:message code="userid" />: ${authInfo.userid} </p>
<p> <spring:message code="name" />: ${authInfo.name} </p>
<p> <spring:message code="birthday" />: <tf:formatDate value="${authInfo.birthday}" pattern="yyyy-MM-dd" /> </p>
<p> <spring:message code="email" />: ${authInfo.email} </p>
<p> <spring:message code="address" />: ${authInfo.address} </p>
<p> <spring:message code="phoneNum" />: ${authInfo.phoneNum} </p>
<a href="/finalexam"> <spring:message code="back" /> </a>
</c:if>

</body>
</html>