<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="insertion.css" rel="stylesheet">

<meta charset="ISO-8859-1">
<title>Insertion Utilisateur</title>
</head>
<body>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="inscription" />
	</jsp:include>
	<jsp:include page="navbar.jsp" />

	<h2>Nouvelle Vente</h2>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrIns'}">
			<p style="color: red">${errModel.errMessage.get('ErrIns')}</p>
		</c:if>
	</c:forEach>
	
	
	
	
	<h4>Article : </h4>
			<input type="text" name="pseudo"
					value="${insModel.utilisateur.pseudo}" required="required" />
			<br>
	
	<h4>Description : </h4>
			<input type="text" name="pseudo"
					value="${insModel.utilisateur.pseudo}" required="required" />
			<br>

	<h4>Categorie : </h4>
	







</body>
</html>