<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
<c:if test="${sessionScope.userCo.admin == true}">
	<a href="${pageContext.request.contextPath}/adminUser">Gestion des Utilisateurs</a>
		<a href="${pageContext.request.contextPath}/adminArtCateg">Gestion des Cat�gories</a>
</c:if>	
<a href="${pageContext.request.contextPath}/ServletVente">Vendre un article</a>
<a href="${pageContext.request.contextPath}/ServletProfil">Mon Profil</a>
<a href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a>