<%@page import="org.eni.encheres.bo.ArticleVendu"%>
<%@page import="java.time.format.DateTimeFormatter"%>
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
		<div class="detailVente-form-div">
			<h1 class="detailVente-form-div-h1">Détail vente</h1>
			<div class="detailVente-form-2div">
				<img class="detailVente-form-2div-img" src="./img/defaultPicture.webp" />
				<div class="detailVente-form-3div">
					<div class="detailVente-form-4div">
						<h3 class="detailVente-form-4div-h3">${requestScope.unArticle.nom}</h3>
					</div>
					<div class="detailVente-form-4div-cust">
						<p>Description:</p>
						<p class="detailVente-form-4div-p" maxlength="300">${requestScope.unArticle.description}</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Catégorie:</p>
						<p>${requestScope.unArticle.categ.libelle}</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Meilleur offre:</p>
						<p>${requestScope.unArticle.prixVente} pts <c:if test="${requestScope.unArticle.prixVente} == 0">par ${requestScope.maxMontantUser}</c:if></p>
					</div>
					<div class="detailVente-form-4div">
						<p>Mise à prix:</p>
						<p>${requestScope.unArticle.prixInit} points</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Fin de l'enchère:</p>
						<p><%=DateTimeFormatter.ofPattern("dd/MM/YYYY").format(((ArticleVendu) request.getAttribute("unArticle")).getDateFin()) %></p>
					</div>
					<div class="detailVente-form-4div">
						<p>Retrait:</p>
						<p>${requestScope.unArticle.user.rue} ${requestScope.unArticle.user.codePostal} ${requestScope.unArticle.user.ville}</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Vendeur:</p>
						<p>${requestScope.unArticle.user.nom}</p>
					</div>
					
					<c:choose>
						<c:when test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.unArticle.user.idUser}">
							<div class="detailVente-form-4div">
								<h4>Vous ne pouvez pas encherir sur votre article</h4>
							</div>
						</c:when>
						<c:when test="${sessionScope.isConnect == true && sessionScope.userId != param.idUser && sessionScope.userCo.bloque == false}">
							<form method="post" action="${pageContext.request.contextPath}/detailVente?idArticle=${param.idArticle}">
								<label for="lblMaProposition">Ma proposition:</label>
								<input id="lblMaProposition" min="${requestScope.unArticle.prixVente == 0 ? requestScope.unArticle.prixInit +1 : requestScope.unArticle.prixVente +1}" type="number" name="maProposition" value="${requestScope.unArticle.prixVente == 0 ? requestScope.unArticle.prixInit +1 : requestScope.unArticle.prixVente +1}" required>
								<input class="venteDetail-submit" type="submit" value="Enchérir">
							</form>
						</c:when>
						<c:otherwise>
							<div class="detailVente-form-4div">
							<c:choose>
								<c:when test="${sessionScope.isConnect == true && sessionScope.userId != param.idUser && sessionScope.userCo.bloque == true}">
									<h4>Vous êtes bloquer</h4>
								</c:when>
								<c:otherwise>
									<h4>Connectez-vous pour participer à l'enchère</h4>
								</c:otherwise>
							</c:choose>
							</div>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>
		</div>
		<a class="detailVente-a-back" href="${pageContext.request.contextPath}/">Retour</a>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>