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
	<title>SearchByProduct</title>
</head>
<body>

<% request.setCharacterEncoding("UTF-8"); %>
<P> <spring:message code="searchresult" /> </P>

<c:choose> 
    <c:when test="${queryResult eq 1}">
		<div style="position: absolute; left: 30px; top: 100px; text-align: center">
			<img src="http://img.danawa.com/prod_img/500000/080/210/img/15210080_1.jpg" width="200" height="200">
			<p> <a href="./product1"> 베이지 니트 가디건 </a> </p>
		</div>
    </c:when>
    <c:when test="${queryResult eq 2}">
		<div style="position: absolute; left: 30px; top: 100px; text-align: center">
			<img src="http://img.danawa.com/prod_img/500000/800/842/img/16842800_1.jpg" width="200" height="200">
			<p> <a href="./product2"> 데님 진 </a> </p>
		</div>
    </c:when>
    <c:when test="${queryResult eq 3}">
		<div style="position: absolute; left: 30px; top: 100px; text-align: center">
			<img src="http://img.danawa.com/prod_img/500000/896/001/img/17001896_1.jpg" width="200" height="200">
			<p> <a href="./product3"> 검정 슬랙스 </a> </p>
		</div>	
    </c:when>
    <c:when test="${queryResult eq 4}">
		<div style="position: absolute; left: 30px; top: 100px; text-align: center">
			<img src="http://img.danawa.com/prod_img/500000/894/793/img/5793894_1.jpg" width="200" height="200">
			<p> <a href="./product4"> 발목 양말 3세트 (하양,회색,검정) </a> </p>
		</div>
    </c:when>
    <c:when test="${queryResult eq 5}">
		<div style="position: absolute; left: 30px; top: 100px; text-align: center">
			<img src="http://img.danawa.com/prod_img/500000/396/474/img/7474396_1.jpg" width="200" height="200">
			<p> <a href="./product5"> 검정 백팩 </a> </p>
		</div>
    </c:when>
    <c:otherwise>
        <p> ${queryResult2} <p>
    </c:otherwise>
</c:choose>

<a href="./"> <spring:message code="back" /> </a>

</body>
</html>