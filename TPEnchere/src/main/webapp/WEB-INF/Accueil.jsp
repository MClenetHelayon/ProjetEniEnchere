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
			<h2 class="filtres-form-h2">Filtres:</h2>
			<input class="filtres-form-inputText" type="search" placeholder="Le nom de l'article contient" value="${requestScope.txt}" name="filtresText">
			<div class="filtres-form-div">
				<%@include file="/WEB-INF/includes/ListCategorie.jsp" %>
			</div>	
			<c:if test="${sessionScope.isConnect == true && sessionScope.userId != null}">
				<div class="filtres-accountConnected">
					<div class="filtres-accountConnected-liste">
						<div>
							<label for="lblAchats">Achats</label>
							<input id="lblAchats" type="radio" name="achatsVentes" value="achats" checked>
						</div>
						<div class="filtres-accountConnected-liste-container-checkbox">
							<div>
								<input id="lblEncheresOuvertes" type="checkbox" name="achats" value="chkEncheresOuvertes">
								<label for="lblEncheresOuvertes">enchères ouvertes</label>
							</div>
							<div>
								<input id="lblMesEncheresEnCours" type="checkbox" name="achats" value="chkMesEncheresEnCours">
								<label for="lblMesEncheresEnCours">mes enchères en cours</label>
							</div>
							<div>
								<input id="lblMesEncheresRemportees" type="checkbox" name="achats" value="chkMesEncheresRemportees">
								<label for="lblMesEncheresRemportees">mes enchères remportées</label>
							</div>
						</div>
					</div>
					<div class="filtres-accountConnected-liste">
						<div>
							<label for="lblMesVentes">Mes Ventes</label>
							<input id="lblMesVentes" type="radio" name="achatsVentes" value="mesVentes">
						</div>
						<div class="filtres-accountConnected-liste-container-checkbox">
							<div>
								<input id="lblMesVenteEnCours" type="checkbox" name="mesVentes" value="chkMesVenteEnCours" disabled>
								<label for="lblMesVenteEnCours">mes ventes en cours</label>
							</div>
							<div>
								<input id="lblVentesNonDébutés" type="checkbox" name="mesVentes" value="chkVentesNonDebutes" disabled>
								<label for="lblVentesNonDébutés">ventes non débutées</label>
							</div>
							<div>
								<input id="lblVentesTerminees" type="checkbox" name="mesVentes" value="chkVentesTerminees" disabled>
								<label for="lblVentesTerminees">ventes terminées</label>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<input type="submit" class="filtres-form-inputSubmit" value="Rechercher">
		</form>
		
		<div class="listeArticle">
			
			<c:forEach var="elem" items="${requestScope.listArticle}">
				
				<div class="article-init">
					<img class="article-img" src="./img/defaultPicture.webp" />
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
							<c:otherwise>
								<a class="article-a">
							</c:otherwise>
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