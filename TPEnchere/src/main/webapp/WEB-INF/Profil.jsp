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
		
		<c:if test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.unUtilisateur.idUser}">
			<div>
				<a class="profil-a" href="${pageContext.request.contextPath}/editProfil">Modifier</a>
			</div>
		</c:if>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>