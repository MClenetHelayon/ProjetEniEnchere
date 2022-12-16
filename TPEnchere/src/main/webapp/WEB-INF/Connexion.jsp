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
		<form class="connexion-form-init" method="post" action="${pageContext.request.contextPath}/back/ServletConnexion">
			<div class="connexion-form-div">
				<label for="lblId">Identifiant:</label>
				<input id="lblId" type="text" name="identifiant" required>
			</div>
			<div class="connexion-form-div">
				<label for="lblPsw">Mot de passe:</label>
				<input id="lblPsw" type="password" name="password" required>
			</div>
			<div class="connexion-form-div">
				<input class="connexion-form-inputSubmit" type="submit" value="Connexion">
				<div class="connexion-form-2div">
					<div>
						<input id="lblCheckbox" type="checkbox" name="alwaysOpen">
						<label for="lblCheckbox">Se Souvenir de moi</label>
					</div>
					<a href="${pageContext.request.contextPath}/ServletMdpOublier">Mot de passe oublié</a>
				</div>
			</div>
		</form>
		<p>${requestScope.erreur }</p>
		<a class="connexion-a" href="${pageContext.request.contextPath}/ServletCreationCompte">Créer un compte</a>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>