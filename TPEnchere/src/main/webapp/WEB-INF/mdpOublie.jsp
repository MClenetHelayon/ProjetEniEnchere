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
		<form class="connexion-form-init" method="post" action="${pageContext.request.contextPath}/forgotMdp">
			<div class="connexion-form-div">
				<label for="lblEmail">Email:</label>
				<input id="lblIEmail" type="text" name="email" required>
			</div>
			<div class="connexion-form-div">
				<label for="lblEmail">Nouveau Mot de Passe:</label>
				<input id="lblIEmail" type="text" name="motDePasse" required>
			</div>
			<div class="connexion-form-div">
				<label for="lblEmail">Confirmer le Mot de Passe:</label>
				<input id="lblIEmail" type="text" name="confirmation" required>
			</div>
			<input class="connexion-form-inputSubmit" type="submit" value="Connexion">
		</form>
		<p>${requestScope.result}</p>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>