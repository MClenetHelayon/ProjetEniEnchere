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
		<form class="editAccount-form-init" method="post" action="${pageContext.request.contextPath}/">
			<%@include file="/WEB-INF/includes/ListProfil.jsp" %>
			
			<div>
				<input class="editAccount-submit" type="submit" value="Enregistrer">
				<a class="supprimer-a" href="${pageContext.request.contextPath}/">Supprimer mon compte</a>
			</div>
		</form>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>