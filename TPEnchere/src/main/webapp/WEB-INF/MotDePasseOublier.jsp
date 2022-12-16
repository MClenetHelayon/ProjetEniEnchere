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
		<form class="motDePasseOublier-form-init" method="post" action="${pageContext.request.contextPath}/">
			<div class="motDePasseOublier-form-div">
				<label for="lblId">Identifiant:</label>
				<input id="lblId" type="text" name="identifiant" required>
			</div>
			<div class="motDePasseOublier-form-div">
				<label for="lblEmail">Email:</label>
				<input id="lblEmail" type="text" name="email" required>
			</div>
			<div class="motDePasseOublier-form-div">
				<input class="motDePasseOublier-form-inputSubmit" type="submit" value="Envoyer">
			</div>
		</form>
		<a class="motDePaddeOublier-a" href="${pageContext.request.contextPath}/ServletCreationCompte">Créer un compte</a>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>