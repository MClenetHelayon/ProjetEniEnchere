<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>				
<c:if test="${sessionScope.userCo.admin == true}">
	<a href="${pageContext.request.contextPath}/adminUser">Gestion des Utilisateurs</a>
		<a href="${pageContext.request.contextPath}/adminArtCateg">Gestion des Catégories</a>
</c:if>	
<a href="${pageContext.request.contextPath}/ServletVente">Vendre un article</a>
<a href="${pageContext.request.contextPath}/ServletProfil">Mon Profil</a>
<a href="${pageContext.request.contextPath}/back/ServletDeconnexion">Déconnexion</a>