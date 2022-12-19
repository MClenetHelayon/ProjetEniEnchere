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
				<%@include file="/WEB-INF/includes/ListCategorie.jsp" %>
				<input type="submit" value="Enregistrer">
		</form>
		<c:choose>
			<c:when test="${!empty requestScope.LArticle}">
				<c:forEach var="a" items="${requestScope.LArticle}">
					<div class="listeArticle">
						<div class="article-init">
							<div><h4 class="article-h4">${a.nom}</h4></div>
							<form class="filtres-form-init" name="accueilFiltres" method="post" action="${pageContext.request.contextPath}/updateArtCateg?idArt=${a.numArticle}">
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
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<h3>Choisissez une option</h3>
			</c:otherwise>
		</c:choose>

		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
	<%@include file="/WEB-INF/includes/scriptJs.jsp" %>
</body>
</html>