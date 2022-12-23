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
		<h1>Historique de l'enchère</h1>
		
		<div class="listeHistorique-init">
			
			<c:forEach var="elem" items="${requestScope.listEnchere}">
				
					<div class="listEnchere-div">
						<p>${elem.user.pseudo}</p>
						<p>${elem.montant} pts</p>
					</div>
					
			</c:forEach>
			
		</div>
		<div>
			<a class="vente-a-back" onclick="return history.back(-1);">Retour</a>
		</div>
		
		<%@include file="/WEB-INF/includes/Erreur.jsp" %>
	</main>
	<%@include file="/WEB-INF/includes/Footer.jsp" %>
</body>
</html>