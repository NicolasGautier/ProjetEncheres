<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="detailvente" />
</jsp:include>

<jsp:include page="navbar.jsp">
	<jsp:param name="hidden" value="true" />
</jsp:include>




<div class="row">
			<div class="col-5"></div>
<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrIns'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrIns')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
	<c:if test="${key == 'ErrInit'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrInit')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>
<div class="col-3"></div>
<div class="col-4"></div>
</div>

<h2>Détail vente</h2>
<h2>${detailVente.articleVendu.nomArticle}</h2>

<div class="container-fluid">

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Description</div>
		<div class="col-1"></div>
		<div class="col-2">
			<textarea>${detailVente.articleVendu.description}</textarea>
		</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Catégorie</div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.articleVendu.categorie.libelle}</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Meilleure offre :</div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.articleVendu.prixVente}points
			par ${detailVente.meilleurEnchere.utilisateurEncherit.pseudo}</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Mise à prix</div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.articleVendu.miseAPrix}</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Fin de l'enchère</div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.articleVendu.dateFinEncheres}</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Retrait</div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.retrait.rue}</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1"></div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.retrait.code_postal}
			${detailVente.retrait.ville}</div>
		<div class="col-3"></div>
	</div>

	<div class="divvide"></div>
	<div class="divvide"></div>

	<div class="row">
		<div class="col-4"></div>
		<div class="col-1">Vendeur</div>
		<div class="col-1"></div>
		<div class="col-2">${detailVente.articleVendu.utilisateurVend.pseudo}</div>
		<div class="col-3"></div>
	</div>
</div>


<c:if
	test="${detailVente.articleVendu.utilisateurVend.noUtilisateur != logModel.utilisateur.noUtilisateur}">
	<form
		action="DetailVenteServlet?id=${detailVente.articleVendu.noArticle}"
		method="post">

		<div class="row">
			<div class="col-4"></div>
			<div class="col-1">Ma proposition</div>
			<div class="col-1"></div>
			<input type="number" id="proposition" name="proposition" min="${detailVente.articleVendu.prixVente + 1}">
			<div class="col-1"></div>
			<div class="col-1"></div>
			<button class="btn-secondary btn-lg"type="submit" id="encherir" name="formulaireEncherir" value="encherir">Enchérir</button>
			<div class="col-1"></div>
			<div class="col-3"></div>
		</div>
	</form>
</c:if>

	



<jsp:include page="footer.jsp" />
