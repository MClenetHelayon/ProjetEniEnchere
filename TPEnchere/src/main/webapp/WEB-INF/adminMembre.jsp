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
		<h1>Liste des utilisateurs</h1>
		<c:forEach var="u" items="${requestScope.LUser}">
			<c:if test="${!u.admin}">
				<div class="listeArticle">
					<c:choose>
						<c:when test="${u.bloque}">
							<div class="article-init" style="background-color: rgba(150,150,150,.7)">
								<div><h4 class="article-h4">${u.pseudo }</h4></div>
								<div><a href="${pageContext.request.contextPath}/adminDelete?id=${u.idUser}">Supprimer</a></div>
								<div><a href="${pageContext.request.contextPath}/adminLock?id=${u.idUser}">Débloquer</a></div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="article-init">
								<div><h4 class="article-h4">${u.pseudo }</h4></div>
								<div><a href="${pageContext.request.contextPath}/adminDelete?id=${u.idUser}">Supprimer</a></div>
								<div><a href="${pageContext.request.contextPath}/adminLock?id=${u.idUser}">Bloquer</a></div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
		</c:forEach>
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
	<%@include file="/WEB-INF/includes/scriptJs.jsp" %>
</body>
</html>