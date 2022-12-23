<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.eni.encheres.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
<%@include file="/WEB-INF/includes/Head.jsp" %>
<body>
	<header>
		<%@include file="/WEB-INF/includes/BouttonAccueil.jsp" %>
		<nav class="header-nav">
		
			<c:choose>
				<c:when test="${sessionScope.isConnect == true}">
					<%@include file="/WEB-INF/includes/header.jsp" %>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/creationCompte">S'inscrire</a>
					<a href="${pageContext.request.contextPath}/connexion">Se connecter</a>
				</c:otherwise>
			</c:choose>
		</nav>
	</header>
	<main>
		<h1>Liste des enchères</h1>
		
		<form class="filtres-form-init" name="accueilFiltres" method="post" action="${pageContext.request.contextPath}/accueil">
			<%@include file="/WEB-INF/includes/Filtre.jsp" %>
			<input type="submit" class="filtres-form-inputSubmit" value="Rechercher">
		</form>
		<div class="listeArticle">	
			<c:forEach var="elem" items="${requestScope.listArticle}">
				
				<c:choose>
					<c:when test="${elem.etatVente==0}">
						<div class="article-init" style="box-shadow:0 0 6px rgba(11, 163, 255, 0.7);">
					</c:when>
					<c:when test="${elem.etatVente==1}">
						<div class="article-init" style="box-shadow:0 0 6px rgba(255, 114, 11, 0.7);">
					</c:when>
					<c:when test="${elem.etatVente==2}">
						<div class="article-init" style="box-shadow: 0 0 6px rgba(110, 255, 11, 0.7)">
					</c:when>
				</c:choose>
				
				
				<c:choose>
					<c:when test="${!empty elem.imgData}">
						<img class="article-img" src="${elem.imgData}"/>
					</c:when>
					<c:otherwise>
						<img class="article-img" src="./img/defaultPicture.webp" />
					</c:otherwise>
				</c:choose>
				
				<div>
				
				<c:choose>
					<c:when test="${elem.etatVente == 2 && sessionScope.isConnect == true && sessionScope.userId == elem.user.idUser}">
						<a class="article-a" href="${pageContext.request.contextPath}/winEnchere?idArticle=${elem.numArticle}&idUser=${elem.user.idUser}">
					</c:when>
					<c:when test="${elem.etatVente == 2}">
						<a class="article-a" href="${pageContext.request.contextPath}/winEnchere?idArticle=${elem.numArticle}">
					</c:when>
					<c:when test="${elem.etatVente == 1}">
						<a class="article-a" href="${pageContext.request.contextPath}/detailVente?idArticle=${elem.numArticle}">
					</c:when>
					<c:when test="${elem.etatVente == 0 && sessionScope.isConnect == true && sessionScope.userId == elem.user.idUser}">
						<a class="article-a" href="${pageContext.request.contextPath}/readVente?idArticle=${elem.numArticle}&idUser=${elem.user.idUser}">
					</c:when>
				</c:choose>
				
						<h4 class="article-h4">${elem.nom}</h4>
					</a>
					<p>Prix: ${elem.prixInit} points</p>
					<fmt:parseDate  value="${elem.dateFin}" type="date" pattern="yyyy-MM-dd" var="parsedDate" />
					<p>Fin de l'enchère: <fmt:formatDate type="date" value="${parsedDate}" pattern="dd/MM/YYYY"/></p>
					<p>Vendeur: <a class="article-a2" href="${pageContext.request.contextPath}/profil${elem.user.idUser != sessionScope.userId ? '?idUser=' += elem.user.idUser : '' }">${elem.user.pseudo}</a></p>
				</div>
			</div>
			</c:forEach>
		</div>
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
	<%@include file="/WEB-INF/includes/scriptJs.jsp" %>
</body>
</html>