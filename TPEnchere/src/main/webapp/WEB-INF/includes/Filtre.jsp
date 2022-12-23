<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="filtres-form-h2">Filtres:</h2>
<input class="filtres-form-inputText" type="search" placeholder="Le nom de l'article contient" value="${requestScope.txt}" name="filtresText">
<div class="filtres-form-div">
	<label>Catégorie:</label>
	<select name="choixCategorie">
		<c:forEach var="elem" items="${requestScope.listCategorie}" varStatus="value">
			<c:choose>
				<c:when test="${elem.numCat==requestScope.idCat}" >
					<option value="${elem.numCat}" selected>${elem.libelle}</option>
				</c:when>
				<c:otherwise>
					<option value="${elem.numCat}">${elem.libelle}</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
</div>	
<c:if test="${sessionScope.isConnect == true && sessionScope.userId != null}">
<c:choose>
<c:when test="${requestScope.achatsCheck}">
	<div class="filtres-accountConnected">
		<div class="filtres-accountConnected-liste">
			<div>
				<label for="lblAchats">Achats</label>
				<input id="lblAchats" type="radio" name="achatsVentes" value="achats" checked>
			</div>
			<div class="filtres-accountConnected-liste-container-checkbox">
				<div>
					<input id="lblEncheresOuvertes" type="checkbox" name="achats0" value="chkEncheresOuvertes" ${requestScope.check0 == true ? "checked" : ""}>
					<label for="lblEncheresOuvertes">enchères ouvertes</label>
				</div>
				<div>
					<input id="lblMesEncheresEnCours" type="checkbox" name="achats1" value="chkMesEncheresEnCours" ${requestScope.check1 == true ? "checked" : ""}>
					<label for="lblMesEncheresEnCours">mes enchères en cours</label>
				</div>
				<div>
					<input id="lblMesEncheresRemportees" type="checkbox" name="achats2" value="chkMesEncheresRemportees" ${requestScope.check2 == true ? "checked" : ""}>
					<label for="lblMesEncheresRemportees">mes enchères remportées</label>
				</div>
			</div>
		</div>
		<div class="filtres-accountConnected-liste">
			<div>
				<label for="lblMesVentes">Mes Ventes</label>
				<input id="lblMesVentes" type="radio" name="achatsVentes" value="ventes">
			</div>
			<div class="filtres-accountConnected-liste-container-checkbox">
				<div>
					<input id="lblVentesNonDébutés" type="checkbox" name="ventes0" value="chkVentesNonDebutes" disabled>
					<label for="lblVentesNonDébutés">ventes non débutées</label>
				</div>
				<div>
					<input id="lblMesVenteEnCours" type="checkbox" name="ventes1" value="chkMesVenteEnCours" disabled>
					<label for="lblMesVenteEnCours">mes ventes en cours</label>
				</div>
				<div>
					<input id="lblVentesTerminees" type="checkbox" name="ventes2" value="chkVentesTerminees" disabled>
					<label for="lblVentesTerminees">ventes terminées</label>
				</div>
			</div>
		</div>
	</div>
</c:when>
<c:when test="${requestScope.ventesCheck}">
	<div class="filtres-accountConnected">
		<div class="filtres-accountConnected-liste">
			<div>
				<label for="lblAchats">Achats</label>
				<input id="lblAchats" type="radio" name="achatsVentes" value="achats">
			</div>
			<div class="filtres-accountConnected-liste-container-checkbox">
				<div>
					<input id="lblEncheresOuvertes" type="checkbox" name="achats0" value="chkEncheresOuvertes" disabled>
					<label for="lblEncheresOuvertes">enchères ouvertes</label>
				</div>
				<div>
					<input id="lblMesEncheresEnCours" type="checkbox" name="achats1" value="chkMesEncheresEnCours" disabled>
					<label for="lblMesEncheresEnCours">mes enchères en cours</label>
				</div>
				<div>
					<input id="lblMesEncheresRemportees" type="checkbox" name="achats2" value="chkMesEncheresRemportees" disabled>
					<label for="lblMesEncheresRemportees">mes enchères remportées</label>
				</div>
			</div>
		</div>
		<div class="filtres-accountConnected-liste">
			<div>
				<label for="lblMesVentes">Mes Ventes</label>
				<input id="lblMesVentes" type="radio" name="achatsVentes" value="ventes" checked>
			</div>
			<div class="filtres-accountConnected-liste-container-checkbox">
				<div>
					<input id="lblVentesNonDébutés" type="checkbox" name="ventes0" value="chkVentesNonDebutes" ${requestScope.check0 == true ? "checked" : ""}>
					<label for="lblVentesNonDébutés">ventes non débutées</label>
				</div>
				<div>
					<input id="lblMesVenteEnCours" type="checkbox" name="ventes1" value="chkMesVenteEnCours" ${requestScope.check1 == true ? "checked" : ""}>
					<label for="lblMesVenteEnCours">mes ventes en cours</label>
				</div>
				<div>
					<input id="lblVentesTerminees" type="checkbox" name="ventes2" value="chkVentesTerminees" ${requestScope.check2 == true ? "checked" : ""}>
					<label for="lblVentesTerminees">ventes terminées</label>
				</div>
			</div>
		</div>
	</div>
</c:when>
<c:otherwise>
		<div class="filtres-accountConnected">
		<div class="filtres-accountConnected-liste">
			<div>
				<label for="lblAchats">Achats</label>
				<input id="lblAchats" type="radio" name="achatsVentes" value="achats" checked>
			</div>
			<div class="filtres-accountConnected-liste-container-checkbox">
				<div>
					<input id="lblEncheresOuvertes" type="checkbox" name="achats0" value="chkEncheresOuvertes">
					<label for="lblEncheresOuvertes">enchères ouvertes</label>
				</div>
				<div>
					<input id="lblMesEncheresEnCours" type="checkbox" name="achats1" value="chkMesEncheresEnCours">
					<label for="lblMesEncheresEnCours">mes enchères en cours</label>
				</div>
				<div>
					<input id="lblMesEncheresRemportees" type="checkbox" name="achats2" value="chkMesEncheresRemportees">
					<label for="lblMesEncheresRemportees">mes enchères remportées</label>
				</div>
			</div>
		</div>
		<div class="filtres-accountConnected-liste">
			<div>
				<label for="lblMesVentes">Mes Ventes</label>
				<input id="lblMesVentes" type="radio" name="achatsVentes" value="ventes">
			</div>
			<div class="filtres-accountConnected-liste-container-checkbox">
				<div>
					<input id="lblVentesNonDébutés" type="checkbox" name="ventes0" value="chkVentesNonDebutes" disabled>
					<label for="lblVentesNonDébutés">ventes non débutées</label>
				</div>
				<div>
					<input id="lblMesVenteEnCours" type="checkbox" name="ventes1" value="chkMesVenteEnCours" disabled>
					<label for="lblMesVenteEnCours">mes ventes en cours</label>
				</div>
				<div>
					<input id="lblVentesTerminees" type="checkbox" name="ventes2" value="chkVentesTerminees" disabled>
					<label for="lblVentesTerminees">ventes terminées</label>
				</div>
			</div>
		</div>
	</div>
</c:otherwise>
</c:choose>
</c:if>