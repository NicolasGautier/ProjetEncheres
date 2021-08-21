<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="accueil" />
</jsp:include>

<jsp:include page="navbar.jsp" />

<div class="row mb-3">
	<div class="justify-content-center">
		<h2>
			Liste des enchères<img alt="image enchere" src="<%=request.getContextPath()%>/image/image_enchere.png">
		</h2>
	</div>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrAcc'}">
			<p style="color: red">${errModel.errMessage.get('ErrAcc')}</p>
		</c:if>
	</c:forEach>
</div>

<div class="row mb-3">
	<form action="AccueilServlet" method="post">
		<div class="col-3 themed-grid-col">
			<label for="filtre">Filtres :</label> 
			<input type="search" name="filtre" value="${accModel.filtre}" /> 
			<label for="categorieSelect">Catégorie :</label> 
			<select name="categorieSelect">
				<option value="Toutes">Toutes</option>
				<c:forEach var="categorie" items="${accModel.lstCategorie}">
					<option value="${categorie.libelle}" <c:if test="${accModel.categorie.libelle == categorie.libelle}">selected</c:if>>${categorie.libelle}</option>
				</c:forEach>				
			</select>
		</div>
		<div class="col-9 themed-grid-col">
			<input type="submit" value="Rechercher" />
		</div>
		<c:if test="${!empty logModel.utilisateur.noUtilisateur}">
			<jsp:include page="achatsMesVentesMenu.jsp" />
		</c:if>
	</form>
</div>
<c:if test="${accModel.lstArticleVendu.size() > 0}">
	<table>
		<thead>
			<tr>
				<th>Image</th>
				<th>Article</th>
				<th>Prix</th>
				<th>Date de fin de l'enchere</th>
				<th>Vendeur</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accModel.lstArticleVendu}" var="articleVendu">
				<tr>
					<td><img alt="image neutre"
						src="<%=request.getContextPath()%>/image/image_informatique.png">
					</td>
					<td>${articleVendu.nomArticle}</td>
					<td>${articleVendu.prixVente} points</td>
					<td>${articleVendu.dateFinEncheres}</td>
					<td><a href="ProfilServlet?id=${articleVendu.utilisateurVend.noUtilisateur}">${articleVendu.utilisateurVend.pseudo}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

</body>
</html>