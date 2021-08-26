<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet">

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
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrIns')}">
				<p style="color: red">${erreur}</p>
			</c:forEach>
		</c:if>
	</c:forEach>

	<h4>Article :</h4>
	<input type="text" name="pseudo" value="${insModel.utilisateur.pseudo}"
		required="required" />
	<br>

	<h4>Description :</h4>
	<input type="text" name="pseudo" value="${insModel.articleVendu.description}"
		required="required" />
	<br>

	<h4>Categorie :</h4>
 			<div class="col-md-3"><label for="categorieSelect">Catégorie :</label>
  				 <select name="categorieSelect">
					<option value="-1">Toutes</option>
			<c:forEach var="categorie" items="${accModel.lstCategorie}">
				<option value="${categorie.noCategorie}"
					<c:if test="${accModel.categorie.noCategorie == categorie.noCategorie}">selected
					</c:if>>${categorie.libelle}</option>
			</c:forEach>
		</select></div>
 
	<h4>Photo de l'article</h4>
TODO : à voir avec Erwan

	<h4>Mise à prix</h4>
		<input type="number" id="miseAPrix" name="miseAPrix" min="${insModel.articleVendu.miseAPrix + 1}">





</body>
</html>