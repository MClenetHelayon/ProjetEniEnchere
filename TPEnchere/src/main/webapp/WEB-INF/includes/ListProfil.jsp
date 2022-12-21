<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="createAccount-form-divContainer">
	<div class="createAccount-form-div">
		<div class="createAccount-form-2div">
			<label for="lblPseudo">Pseudo:</label>
			<input id="lblPseudo" type="text" name="pseudo" value="${requestScope.unUtilisateur.pseudo}" required>
		</div>
		<div class="createAccount-form-2div">
			<label for="lblNom">Nom:</label>
			<input id="lblNom" type="text" name="nom" value="${requestScope.unUtilisateur.nom}" required>
		</div>
	</div>
	<div class="createAccount-form-div">
		<div class="createAccount-form-2div">
			<label for="lblPrenom">Prénom:</label>
			<input id="lblPrenom" type="text" name="prenom" value="${requestScope.unUtilisateur.prenom}" required>
		</div>
		<div class="createAccount-form-2div">
			<label for="lblEmail">Email:</label>
			<input id="lblEmail" type="text" name="email" value="${requestScope.unUtilisateur.email}" required>
		</div>
	</div>
	<div class="createAccount-form-div">
		<div class="createAccount-form-2div">
			<label for="lblTelephone">Téléphone:</label>
			<input id="lblTelephone" type="text" name="telephone" value="${requestScope.unUtilisateur.telephone}" required>
		</div>
		<div class="createAccount-form-2div">
			<label for="lblRue">Rue:</label>
			<input id="lblRue" type="text" name="rue" value="${requestScope.unUtilisateur.rue}" required>
		</div>
	</div>
	<div class="createAccount-form-div">
		<div class="createAccount-form-2div">
			<label for="lblCodePostal">Code Postal:</label>
			<input id="lblCodePostal" type="text" name="codePostal" value="${requestScope.unUtilisateur.codePostal}" required>
		</div>
		<div class="createAccount-form-2div">
			<label for="lblVille">Ville:</label>
			<input id="lblVille" type="text" name="ville" value="${requestScope.unUtilisateur.ville}" required>
		</div>
	</div>
	
	<c:if test="${sessionScope.isConnect == true}">
		<div class="createAccount-form-div">
			<div class="createAccount-form-2div">
				<label for="lblMdPActuel">Mot de passe actuel:</label>
				<input id="lblMdPActuel" type="password" name="motDePasseOld"  value="${requestScope.unUtilisateur.mdp}" required>
			</div>
		</div>
	</c:if>
	
	<div class="createAccount-form-div">
		<div class="createAccount-form-2div">
			<label for="lblMotDePasse">Nouveau mot de passe:</label>
			<input id="lblMotDePasse" type="password" name="motDePasse" required>
		</div>
		<div class="createAccount-form-2div">
			<label for="lblConfirmation">Confirmation:</label>
			<input id="lblConfirmation" type="password" name="confirmation" required>
		</div>
	</div>
	
	<c:if test="${sessionScope.isConnect && sessionScope.userId == elem.user.idUser}">
		<div class="createAccount-form-div">
			<div class="createAccount-form-2div">
				<label>Credit:</label>
				<p>${sessionScope.userCo.credit}<p>
			</div>
		</div>
	</c:if>
	
</div>