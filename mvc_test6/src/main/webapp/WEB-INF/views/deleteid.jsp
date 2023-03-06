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
<title>회원탈퇴</title>
</head>
<body>
<P> 탈퇴가 완료되었습니다. </P>
<a href="./"> <spring:message code="back" /> </a>
</body>
</html>