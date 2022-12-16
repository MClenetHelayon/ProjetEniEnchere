<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<%@include file="/WEB-INF/includes/Head.jsp" %>
<body>
	<header>
		<%@include file="/WEB-INF/includes/BouttonAccueil.jsp" %>
		<nav class="header-nav">
			<%@include file="/WEB-INF/includes/header.jsp" %>
		</nav>
	</header>
	<main>
		<h1>Liste des utilisateurs</h1>

		<form class="filtres-form-init" name="accueilFiltres">
			<h2 class="filtres-form-h2">Filtres:</h2>
			<input class="filtres-form-inputText" type="search" placeholder="Le nom de l'article contient" name="filtresText">
			<span class="filtres-form-inputSubmit">Rechercher</span>
		</form>
		<c:forEach var="u" items="${requestScope.LUser}">
			<c:if test="${!u.admin}">
			<div class="listeArticle">
				<div class="article-init">
					<div><h4 class="article-h4">${u.pseudo }</h4></div>
					<div><a href="${pageContext.request.contextPath}/adminDelete?id=${u.idUser}">Supprimer</a></div>
					<div><a href="#">Bloquer</a></div>
				</div>
			</div>
			</c:if>
		</c:forEach>
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
	<%@include file="/WEB-INF/includes/scriptJs.jsp" %>
</body>
</html>