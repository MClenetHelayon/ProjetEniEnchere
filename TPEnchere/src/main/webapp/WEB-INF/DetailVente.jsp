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
						<h3 class="detailVente-form-4div-h3">PC Gamer pour travailler</h3>
					</div>
					<div class="detailVente-form-4div-cust">
						<p>Description:</p>
						<p class="detailVente-form-4div-p" maxlength="300">blabla Informatique Informatique Informatique Informatique Informatique Informatique Informatique InformatiqueInformatiqueInformatiqueInformatiqueInformatiqueInformatiqueInformatique  InformatiqueInformatiqueInformatiqueInformatique Informatique Informatique Informatique Informatique Informatique</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Catégorie:</p>
						<p>Informatique</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Meilleur offre:</p>
						<p>210 pts par Bob</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Mise à prix:</p>
						<p>185 points</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Fin de l'enchère:</p>
						<p>09/10/2018</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Retrait:</p>
						<p>10 allée des Alouettes 44880 Saint-Herblain</p>
					</div>
					<div class="detailVente-form-4div">
						<p>Vendeur:</p>
						<p>jojo44</p>
					</div>	
					<c:choose>
						<c:when test="${sessionScope.isConnect == true && sessionScope.userId == param.idUser}">
							<form method="post" action="${pageContext.request.contextPath}/">
								<label for="lblMaProposition">Ma proposition:</label>
								<input id="lblMaProposition" min="1" type="number" name="maProposition" value="0" required>
								<input class="venteDetail-submit" type="submit" value="Enchérir">
							</form>
						</c:when>
						<c:otherwise>
							<div class="detailVente-form-4div">
								<h4>Connectez-vous pour participer à l'enchère</h4>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>