<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="vente-form-init" method="post" action="${pageContext.request.contextPath}/ServletVente"> <!-- enctype="multipart/form-data" -->
	<div class="vente-form-div">
		<h1 class="vente-form-div-h1">Nouvelle vente</h1>
		<div class="vente-form-2div">
			<img class="vente-form-2div-img" src="./img/defaultPicture.webp" />
			<div class="vente-form-3div">
				<div class="vente-form-4div">
					<label for="lblArticle">Article:</label>
					<input id="lblArticle" type="text" name="article" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblDescription">Description:</label>
					<textarea id="lblDescription" name="description" minlength="30" maxlength="300" required></textarea>
				</div>
				<div class="vente-form-4div">
					<%@include file="/WEB-INF/includes/ListCategorie.jsp" %>
				</div>
				<div class="vente-form-4div">
					<label>Photo de l'article:</label>
					<label for="lblPhotoArticle">Uploader</label>
					<input id="lblPhotoArticle" type="file" accept="image/*" name="photo" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblMiseAPrix">Mise à prix:</label>
					<input id="lblMiseAPrix" type="number" name="miseAPrix" value="0" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblDebutEnchère">Début de l'enchère:</label>
					<input id="lblDebutEnchère" type="date" name="dateFinEnchere" required>
				</div>
				<div class="vente-form-4div">
					<label for="lblFinEnchère">Fin de l'enchère:</label>
					<input id="lblFinEnchère" type="date" name="dateDebutEnchere" required>
				</div>
				
				<fieldset class="vente-form-3div-filedset">
					<legend class="vente-form-3div-filedset-legend">Retrait</legend>
					<div class="vente-form-3div-filedset-div">
						<label for="lblRue">Rue:</label>
						<input id="lblRue" type="text" name="rue" value="${requestScope.RetraitUserById.rue}" required>
					</div>
					<div class="vente-form-3div-filedset-div">
						<label for="lblCodePostal">Code postal:</label>
						<input id="lblCodePostal" type="text" name="codePostal" value="${requestScope.RetraitUserById.codePostal}" required>
					</div>
					<div class="vente-form-3div-filedset-div">
						<label for="lblVille">Ville:</label>
						<input id="lblVille" type="text" name="ville" value="${requestScope.RetraitUserById.ville}" required>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
	<div>
		<input type="submit" class="vente-a-save" value="Enregistrer">		
		<a class="vente-a-back" href="${pageContext.request.contextPath}/">Annuler</a>
		
		<c:if test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.idUser}">
			<a class="vente-a-removeArticle" href="${pageContext.request.contextPath}/AnnulerArticle?idArticle=${requestScope.idArticle}&idUser=${requestScope.idUser}">Annuler la vente</a>
		</c:if>
	</div>
</form>