<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty requestScope.statut}">
	<div class="container-${requestScope.statut}">
	
		<jsp:include page="/WEB-INF/includes/infoErreur.jsp">
			<jsp:param value="${requestScope.info}" name="parametre"/>
		</jsp:include>
		
	</div>

</c:if>