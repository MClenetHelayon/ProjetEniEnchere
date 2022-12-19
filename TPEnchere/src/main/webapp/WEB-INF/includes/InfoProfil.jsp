<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="profil-container">
	<div class="profil-container-div">
		<p>Pseudo:</p>
		<p>${sessionScope.userCo.pseudo}</p>		
	</div>
	<div class="profil-container-div">
		<p>Nom:</p>
		<p>${sessionScope.userCo.nom}</p>		
	</div>
	<div class="profil-container-div">
		<p>Prénom:</p>
		<p>${sessionScope.userCo.prenom}</p>		
	</div>
	<div class="profil-container-div">
		<p>Email:</p>
		<p>${sessionScope.userCo.email}</p>		
	</div>
	<div class="profil-container-div">
		<p>Téléphone:</p>
		<p>${sessionScope.userCo.telephone}</p>		
	</div>
	<div class="profil-container-div">
		<p>Rue:</p>
		<p>${sessionScope.userCo.rue}</p>		
	</div>
	<div class="profil-container-div">
		<p>Code postal:</p>
		<p>${sessionScope.userCo.codePostal}</p>		
	</div>
	<div class="profil-container-div">
		<p>Ville:</p>
		<p>${sessionScope.userCo.ville}</p>		
	</div>
	<div class="profil-container-div">
		<p>Credit:</p>
		<p>${sessionScope.userCo.credit}</p>		
	</div>
</div>