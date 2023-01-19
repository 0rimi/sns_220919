<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>	
<div class="d-flex align-items-center h-100 justify-content-between">
	
	<div class="ml-3">
		<h1><a href="/timeline/timeline_view">Rimstagram</a></h1>
	</div>
	
	
	<%-- 로그인 후 --%>
	<c:if test="${not empty userId}">
		<div class="mr-4">
			<img class="profile" src="${profileUrl}">
			<span>${userName } 님</span>
			<a href="/user/sign_out" class="ml-2 font-weight-bold">로그아웃</a>
		</div>
	</c:if>
	<!-- 로그인 전 -->
	<c:if test="${empty userId}">
		<div class="mr-4">
			<a href="/user/sign_in_view" class="ml-3 font-weight-bold">로그인</a>
			<a href="/user/sign_up_view" class="ml-2 font-weight-bold">회원가입</a>
		</div>
	</c:if>
	
</div>
