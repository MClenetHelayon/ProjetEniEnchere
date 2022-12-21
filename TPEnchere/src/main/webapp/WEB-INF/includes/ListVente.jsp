<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form class="vente-form-init" method="post" action="${pageContext.request.contextPath}/vente"> <!-- enctype="multipart/form-data" -->
	<div class="vente-form-div">
		
		<c:choose>
			<c:when test="${requestScope['javax.servlet.forward.servlet_path'] == '/readVente'}">
				<h1 class="vente-form-div-h1">Edit vente</h1>
			</c:when>
			<c:otherwise>
				<h1 class="vente-form-div-h1"> Nouvelle vente</h1>
			</c:otherwise>
		</c:choose>
		
		<div class="vente-form-2div">
			<img class="vente-form-2div-img" src="./img/defaultPicture.webp" />
			<div class="vente-form-3div">
				<div class="vente-form-4div">
					<label for="lblArticle">Article:</label>
					<input id="lblArticle" type="text" name="article" value="${requestScope.unArticle.nom}" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblDescription">Description:</label>
					<textarea id="lblDescription" name="description" minlength="30" maxlength="300" required>${requestScope.unArticle.description}</textarea>
				</div>
				<div class="vente-form-4div">
					<%@include file="/WEB-INF/includes/ListCategorie.jsp" %>
				</div>
				<div class="vente-form-4div">
					<label>Photo de l'article:</label>
					<label for="lblPhotoArticle">Uploader</label>
					<input id="lblPhotoArticle" type="file" accept="image/png, image/jpeg, image/jpg, image/webp" name="photo" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblMiseAPrix">Mise à prix:</label>
					<input id="lblMiseAPrix" type="number" name="miseAPrix" value="${empty requestScope.unArticle.prixInit ? 0 : requestScope.unArticle.prixInit}" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblDebutEnchère">Début de l'enchère:</label>
					<input id="lblDebutEnchère" type="date" name="dateFinEnchere" value="<%= LocalDate.now().plusDays(1) %>" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblFinEnchère">Fin de l'enchère:</label>
					<input id="lblFinEnchère" type="date" name="dateDebutEnchere" value="${requestScope.unArticle.dateFin}" required>
				</div>
				
				<fieldset class="vente-form-3div-filedset">
					<legend class="vente-form-3div-filedset-legend">Retrait</legend>
					<div class="vente-form-3div-filedset-div">
						<label for="lblRue">Rue:</label>
						<input id="lblRue" type="text" name="rue" value="${requestScope.RetraitUserById.rue}">
					</div>
					<div class="vente-form-3div-filedset-div">
						<label for="lblCodePostal">Code postal:</label>
						<input id="lblCodePostal" type="text" name="codePostal" value="${requestScope.RetraitUserById.codePostal}">
					</div>
					<div class="vente-form-3div-filedset-div">
						<label for="lblVille">Ville:</label>
						<input id="lblVille" type="text" name="ville" value="${requestScope.RetraitUserById.ville}">
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<div>
	
		<c:if test="${!userCo.bloque}">
			<input type="submit" class="vente-a-save" value="Enregistrer">
		</c:if>
			
		<a class="vente-a-back" href="${pageContext.request.contextPath}/">Retour</a>
		
		<c:if test="${sessionScope.isConnect == true && sessionScope.userId == param.idUser}">
			<a class="vente-a-removeArticle" href="${pageContext.request.contextPath}/annuleVente?idArticle=${param.idArticle}&idUser=${param.idUser}">Annuler la vente</a>
		</c:if>
		
	</div>
</form>