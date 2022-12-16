<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<label>Catégorie:</label>
<select name="choixCategorie">
	<c:forEach var="elem" items="${requestScope.listCategorie}" varStatus="value">
		<c:choose>
			<c:when test="${value} == 0">
				<option value="${elem.numCat}" selected>${elem.libelle}</option>
			</c:when>
			<c:otherwise>
				<option value="${elem.numCat}">${elem.libelle}</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
</select>