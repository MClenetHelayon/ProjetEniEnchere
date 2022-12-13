<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<%@include file="/WEB-INF/includes/Head.jsp" %>
<body>
	<header>
		<%@include file="/WEB-INF/includes/BouttonAccueil.jsp" %>
	</header>
	<main>
		<%@include file="/WEB-INF/includes/InfoProfil.jsp" %>
		
		<c:if test="true">
			<div>
				<a href="${pageContext.request.contextPath}/ServletEditProfil">Modifier</a>
			</div>
		</c:if>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>