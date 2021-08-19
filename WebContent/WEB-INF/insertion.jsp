
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

	<h2>Mon profil</h2>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrIns'}">
			<p style="color: red">${errModel.errMessage.get('ErrIns')}</p>
		</c:if>
	</c:forEach>
	<form action="InsererProfilServlet" method="post">
		
			<h4>Pseudo :</h4>
			<input type="text" name="pseudo"
				value="${insModel.utilisateur.pseudo}" required="required" /> <br>
			<h4>Prénom :</h4>
			<input type="text" name="prenom"
				value="${insModel.utilisateur.prenom}" required="required" /> <br>
			<h4>Téléphone :</h4>
			<input type="text" name="telephone"
				value="${insModel.utilisateur.telephone}" /> <br>
			<h4>Code Postal :</h4>
			<input type="text" name="cp"
				value="${insModel.utilisateur.codePostal}" required="required" /> <br>
			<h4>Mot de Passe :</h4>
			<input type="password" name="password"
				value="${insModel.utilisateur.motDePasse}" required="required" /> <br>
		
	</form>

	<form action="InsererProfilServlet" method="post">

			<h4>Nom :</h4>
			<input type="text" name="nom" value="${insModel.utilisateur.nom}"
				required="required" /> <br>
			<h4>Email :</h4>
			<input type="email" name="email"
				value="${insModel.utilisateur.email}" required="required" /> <br>
			<h4>Rue :</h4>
			<input type="text" name="rue" value="${insModel.utilisateur.rue}"
				required="required" /> <br>
			<h4>Ville :</h4>
			<input type="text" name="ville" value="${insModel.utilisateur.ville}"
				required="required" /> <br>
			<h4>Confirmation :</h4>
			<input type="password" name="Pseudo"
				value="${model.utilisateur.confirmation}" />
		
	</form>
	<br>

	<button type="submit" name="formulaireInscription" value="creer">Creer</button>
	<button type="submit" name="formulaireInscription" value="annuler">Annuler</button>

	</form>
	</DIV>

</body>
</html>
<jsp:include page="footer.jsp" />


