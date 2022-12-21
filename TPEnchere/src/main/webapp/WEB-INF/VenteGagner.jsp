<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.eni.encheres.back.bo.ArticleVendu"%>
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
			<c:choose>
				<c:when test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.miseMaxUtilisateur.idUser}">
					<h1 class="detailVente-form-div-h1">Vous avez remporté la vente</h1>
				</c:when>
				<c:otherwise>
					<h1 class="detailVente-form-div-h1">${requestScope.miseMaxUtilisateur.nom} à remporté la vente</h1>
				</c:otherwise>
			</c:choose>
			
			<div class="detailVente-form-2div">
				<img class="detailVente-form-2div-img" src="./img/defaultPicture.webp" />
				<div class="detailVente-form-3div">
					<div class="detailVente-form-4div">
						<h3 class="detailVente-form-4div-h3">${requestScope.unArticle.nom}</h3>
					</div>
					<div class="detailVente-form-4div-cust">
						<p>Description:</p>
						<p class="detailVente-form-4div-p">${requestScope.unArticle.description}</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Meilleur offre:</p>
						
						<c:choose>
							<c:when test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.miseMaxUtilisateur.idUser}">
								<p>${requestScope.unArticle.enchereMax.montant} pts</p>
							</c:when>
							<c:otherwise>
								<p>${requestScope.unArticle.enchereMax.montant} pts par ${requestScope.miseMaxUtilisateur.nom}</p>
							</c:otherwise>
						</c:choose>
						
						
					</div>
					<div class="detailVente-form-4div">
						<p>Mise à prix:</p>
						<p>${requestScope.unArticle.prixInit} points</p>
					</div>
					
					<!-- si other win -->
					
					<c:if test="${sessionScope.userId != requestScope.miseMaxUtilisateur.idUser}">
						<div class="detailVente-form-4div">
							<p>Fin de l'enchère:</p>
							<p><%=DateTimeFormatter.ofPattern("dd/MM/YYYY").format(((ArticleVendu) request.getAttribute("unArticle")).getDateFin()) %></p>
						</div>
					</c:if>
					
					<!-- / -->
					
					<div class="detailVente-form-4div">
						<p>Retrait:</p>
						<p>${requestScope.leVendeur.rue} ${requestScope.leVendeur.codePostal} ${requestScope.leVendeur.ville}</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Vendeur:</p>
						<p>${requestScope.leVendeur.nom}</p>
					</div>
					
					<!-- si j'ai win -->
					
					<c:if test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.miseMaxUtilisateur.idUser}">
						<div class="detailVente-form-4div">
							<p>Tel:</p>
							<p>${requestScope.leVendeur.telephone}</p>
						</div>
					</c:if>
					
					<!-- / -->
					
				</div>
			</div>
		</div>
		<div>
			
			<c:if test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.miseMaxUtilisateur.idUser || sessionScope.userId == requestScope.leVendeur.idUser}">
				<a class="detailVente-a-back" href="${pageContext.request.contextPath}/ServletFinaliser">Retrait effectué</a>
			</c:if>
			
			<a class="detailVente-a-back" href="${pageContext.request.contextPath}/">Retour</a>
		</div>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>