<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
<c:if test="${sessionScope.userCo.admin == true}">
	<a href="${pageContext.request.contextPath}/adminUser">Gestion des Utilisateurs</a>
		<a href="${pageContext.request.contextPath}/adminArtCateg">Gestion des Catégories</a>
</c:if>	
<c:if test="${sessionScope.userCo.bloque != true}">
	<a href="${pageContext.request.contextPath}/vente">Vendre un article</a>
</c:if>
<a href="${pageContext.request.contextPath}/profil">Mon Profil</a>
<a href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>