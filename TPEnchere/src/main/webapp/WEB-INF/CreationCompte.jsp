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
		<h1>Mon profil</h1>
		<form class="createAccount-form-init" method="post" action="${pageContext.request.contextPath}/ServletInscription">
			<%@include file="/WEB-INF/includes/ListProfil.jsp" %>
			
			<div>
				<input class="createAccount-submit" type="submit" value="Créer">
				<a class="connexion-a" href="${pageContext.request.contextPath}/">Annuler</a>
			</div>
		</form>
		<p>${requestScope.result}</p>
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
	<%@include file="/WEB-INF/includes/scriptJs.jsp" %>
</body>
</html>