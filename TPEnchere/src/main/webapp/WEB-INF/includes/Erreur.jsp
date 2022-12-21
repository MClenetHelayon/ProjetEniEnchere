<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-erreur">
	<c:if test="${not empty requestScope.erreur}">
		<jsp:include page="/WEB-INF/includes/infoErreur.jsp">
			<jsp:param value="${requestScope.erreur}" name="parametre"/>
		</jsp:include>
	</c:if>
</div>