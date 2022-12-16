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
				<c:when test="true">
					<h1 class="detailVente-form-div-h1">Vous avez remporté la vente</h1>
				</c:when>
				<c:otherwise>
					<h1 class="detailVente-form-div-h1">User à remporté la vente</h1>
				</c:otherwise>
			</c:choose>
			
			<div class="detailVente-form-2div">
				<img class="detailVente-form-2div-img" src="./img/defaultPicture.webp" />
				<div class="detailVente-form-3div">
					<div class="detailVente-form-4div">
						<h3 class="detailVente-form-4div-h3">PC Gamer pour travailler</h3>
					</div>
					<div class="detailVente-form-4div-cust">
						<p>Description:</p>
						<p class="detailVente-form-4div-p">blabla Informatique Informatique Informatique Informatique Informatique Informatique Informatique InformatiqueInformatiqueInformatiqueInformatiqueInformatiqueInformatiqueInformatique  InformatiqueInformatiqueInformatiqueInformatique Informatique Informatique Informatique Informatique Informatique</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Meilleur offre:</p>
						<p>210 pts par Bob</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Mise à prix:</p>
						<p>185 points</p>
					</div>
					
					<!-- si other win -->
					
					<c:if test="">
						<div class="detailVente-form-4div">
							<p>Fin de l'enchère:</p>
							<p>09/10/2018</p>
						</div>
					</c:if>
					
					<!-- / -->
					
					<div class="detailVente-form-4div">
						<p>Retrait:</p>
						<p>10 allée des Alouettes 44880 Saint-Herblain</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Vendeur:</p>
						<p>jojo44</p>
					</div>
					
					<!-- si j'ai win -->
					
					<c:if test="">
						<div class="detailVente-form-4div">
							<p>Fin de l'enchère:</p>
							<p>09/10/2018</p>
						</div>
					</c:if>
					
					<!-- / -->
					
				</div>
			</div>
		</div>
		<div>
			
			<c:choose>
				<c:when test="">
					<a class="detailVente-a-back" href="${pageContext.request.contextPath}/">Retrait effectué</a>
				</c:when>
				<c:otherwise>
					<a class="detailVente-a-back" href="${pageContext.request.contextPath}/">Annuler</a>
				</c:otherwise>
			</c:choose>
			
		</div>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>