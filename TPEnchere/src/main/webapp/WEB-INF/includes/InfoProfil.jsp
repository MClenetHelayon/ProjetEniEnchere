<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="profil-container">
	<div class="profil-container-div">
		<p>Pseudo:</p>
		<p>${requestScope.unUtilisateur.pseudo}</p>		
	</div>
	<div class="profil-container-div">
		<p>Nom:</p>
		<p>${requestScope.unUtilisateur.nom}</p>		
	</div>
	<div class="profil-container-div">
		<p>Prénom:</p>
		<p>${requestScope.unUtilisateur.prenom}</p>		
	</div>
	<div class="profil-container-div">
		<p>Email:</p>
		<p>${requestScope.unUtilisateur.email}</p>		
	</div>
	<div class="profil-container-div">
		<p>Téléphone:</p>
		<p>${requestScope.unUtilisateur.telephone}</p>		
	</div>
	<div class="profil-container-div">
		<p>Rue:</p>
		<p>${requestScope.unUtilisateur.rue}</p>		
	</div>
	<div class="profil-container-div">
		<p>Code postal:</p>
		<p>${requestScope.unUtilisateur.codePostal}</p>		
	</div>
	<div class="profil-container-div">
		<p>Ville:</p>
		<p>${requestScope.unUtilisateur.ville}</p>		
	</div>
	<c:if test="${sessionScope.isConnect == true && sessionScope.userId == requestScope.unUtilisateur.idUser}">
		<div class="profil-container-div">
			<p>Credit:</hp4>
			<p>${requestScope.unUtilisateur.credit}</p>		
		</div>
	</c:if>
</div>