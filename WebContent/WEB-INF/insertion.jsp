
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		Pseudo : <input type="text" name="pseudo" value="${insModel.utilisateur.pseudo}" required="required"/><br>
		Prénom : <input type="text" name="prenom" value="${insModel.utilisateur.prenom}" required="required"/><br>
		Téléphone : <input type="text" name="telephone" value="${insModel.utilisateur.telephone}"/><br>
		Code Postal : <input type="text" name="cp" value="${insModel.utilisateur.codePostal}" required="required"/><br>
		Mot de Passe : <input type="password" name="newPassword" required="required"/><br>
		Nom : <input type="text" name="nom" value="${insModel.utilisateur.nom}" required="required"/><br>
		Email : <input type="email" name="email" value="${insModel.utilisateur.email}" required="required"/><br>
		Rue : <input type="text" name="rue" value="${insModel.utilisateur.rue}" required="required"/><br>
		Ville : <input type="text" name="ville" value="${insModel.utilisateur.ville}" required="required"/><br>
		Confirmation : <input type="password" name="confPassword" required="required"/><br>
		<button type="submit" name="formulaireInscription" value="creer">Creer</button>	
</form>	
<form action="InsererProfilServlet" method="post">
	<button type="submit" name="formulaireAnnulation" value="annuler" >Annuler</button>
</form>
				
<jsp:include page="footer.jsp"/>

