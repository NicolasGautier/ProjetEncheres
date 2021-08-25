
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="inscription" />
</jsp:include>
<jsp:include page="navbar.jsp" />

<h2>Mon profil</h2>

<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrIns'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrIns')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>

	<div class="divvide"></div>	<div class="divvide"></div>	<div class="divvide"></div>


<form action="InsererProfilServlet" method="post">
	<div class="container-fluid">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Pseudo :</div>
			<div class="col-2">
				<input type="text" name="pseudo"
					value="${insModel.utilisateur.pseudo}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Nom :</div>
			<div class="col-2">
				<input type="text" name="nom" value="${insModel.utilisateur.nom}"
					required="required" />
			</div>
			<div class="col-1"></div>
		</div>

		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Prenom :</div>
			<div class="col-2">
				<input type="text" name="pseudo"
					value="${insModel.utilisateur.prenom}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Email :</div>
			<div class="col-2">
				<input type="text" name="nom" value="${insModel.utilisateur.email}"
					required="required" />
			</div>
			<div class="col-1"></div>
		</div>

		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Téléphone :</div>
			<div class="col-2">
				<input type="text" name="pseudo"
					value="${insModel.utilisateur.telephone}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Rue :</div>
			<div class="col-2">
				<input type="text" name="nom" value="${insModel.utilisateur.rue}"
					required="required" />
			</div>
			<div class="col-1"></div>
		</div>

			<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Code Postal :</div>
			<div class="col-2">
				<input type="text" name="pseudo"
					value="${insModel.utilisateur.codePostal}" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Ville :</div>
			<div class="col-2">
				<input type="text" name="nom" value="${insModel.utilisateur.ville}"
					required="required" />
			</div>
			<div class="col-1"></div>
		</div>

		<div class="divvide"></div>

		<div class="row">
			<div class="col-1"></div>
			<div class="col-2">Mot de Passe :</div>
			<div class="col-2">
				<input type="password" name="newPassword" required="required" />
			</div>
			<div class="col-1"></div>
			<div class="col-2">Confirmation:</div>
			<div class="col-2">
				<input type="password" name="confPassword" required="required" />
			</div>
			<div class="col-1"></div>
		</div>


		<div class="divvide"></div>
	<div class="divvide"></div>	<div class="divvide"></div>	<div class="divvide"></div>
	
	
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4"><button class="btn-secondary btn-lg"type="submit" name="formulaireInscription" value="creer">Créer</button></div>
		<div class="col-2"><button class="btn-secondary btn-lg"type="submit" name="formulaireAnnulation" value="annuler">Annuler</button></div>
		<div class="col-2"></div>
	</div>
		</div>


</form>

<jsp:include page="footer.jsp" />

