<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<%@include file="/WEB-INF/includes/Head.jsp" %>
<body>
	<header>
		<%@include file="/WEB-INF/includes/BouttonAccueil.jsp" %>
		<nav class="header-nav">
			<%@include file="/WEB-INF/includes/header.jsp" %>
		</nav>
	</header>
	<main>
		<h1>Liste des Catégories</h1>
		<form class="filtres-form-init" name="accueilFiltres" method="post" action="${pageContext.request.contextPath}/adminArtCateg">
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
				<input type="submit" value="Enregistrer">
		</form>
		
		<div class="listeArticle">
		
			<c:choose>
				<c:when test="${!empty requestScope.LArticle}">
					<c:forEach var="a" items="${requestScope.LArticle}">
						<div class="article-init">
							<h4 class="article-h4">${a.nom}</h4>
							<form style="display: inherit; grid-gap: 1rem;" name="accueilFiltres" method="post" action="${pageContext.request.contextPath}/updateArtCateg?idArt=${a.numArticle}">
								<select name="newChoixCategorie">
									<c:forEach var="elem" items="${requestScope.listCategorie}" varStatus="value">
										<c:if test="${elem.numCat!=1}">
											<c:choose>
												<c:when test="${a.categ.numCat == elem.numCat}">
													<option value="${elem.numCat}" selected>${elem.libelle}</option>
												</c:when>
												<c:otherwise>
													<option value="${elem.numCat}">${elem.libelle}</option>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</select>
								<input type="submit" value="Enregistrer">
							</form>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="article-init">
						<h3>Choisissez une option</h3>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
	<%@include file="/WEB-INF/includes/scriptJs.jsp" %>
</body>
</html>