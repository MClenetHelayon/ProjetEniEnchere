<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<a href="${pageContext.request.contextPath}/">
		<img class="header-div-img" src="${pageContext.request.contextPath}/img/logo.webp" />
		<%= application.getInitParameter("NAME_SITE") %></a>
</div>