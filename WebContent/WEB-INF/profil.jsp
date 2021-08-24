<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="profil" />  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>
	
	<c:forEach var="key" items="${errModel.err}">		
		<c:if test="${key == 'ErrPar'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrPar')}">
				<p style="color:red">${erreur}</p>
			</c:forEach>	
		</c:if>	
		<c:if test="${key == 'ErrGet'}">
			<c:forEach var="erreur" items="${errModel.errMessages.get('ErrGet')}">
				<p style="color:red">${erreur}</p>
			</c:forEach>	
		</c:if>	
	</c:forEach>

	<div>
		<label>Pseudo:</label><div>${profModel.utilisateur.pseudo}</div>
	</div>
	<div>
		<label>Nom:</label><div>${profModel.utilisateur.nom}</div>
	</div>
	<div>
		<label>Prénom:</label><div>${profModel.utilisateur.prenom}</div>
	</div>
	<div>
		<label>Email:</label><div>${profModel.utilisateur.email}</div>
	</div>
	<div>
		<label>Téléphone:</label><div>${profModel.utilisateur.telephone}</div>
	</div>
	<div>
		<label>Rue:</label><div>${profModel.utilisateur.rue}</div>
	</div>
	<div>
		<label>Code Postal:</label><div>${profModel.utilisateur.codePostal}</div>
	</div>
	<div>
		<label>Ville:</label><div>${profModel.utilisateur.ville}</div>
	</div>
	
	<c:if test="${profModel.utilisateur.noUtilisateur == logModel.utilisateur.noUtilisateur}">
		<a class="btn btn-primary" href="ModifierProfilServlet">Modifier</a>
	</c:if>
	
<jsp:include page="footer.jsp"/>