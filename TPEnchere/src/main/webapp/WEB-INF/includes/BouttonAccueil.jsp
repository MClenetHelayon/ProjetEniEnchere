<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header-div-a">
	<a href="${pageContext.request.contextPath}/">
		<img class="header-div-a-img" src="${pageContext.request.contextPath}/img/logo.webp" />
		<%= application.getInitParameter("NAME_SITE") %>
	</a>
</div>