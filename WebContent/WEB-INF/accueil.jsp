<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="accueil" />
</jsp:include>

<jsp:include page="navbar.jsp" />

<div class="row mb-3">
	<div class="justify-content-center">
		<h2>Liste des enchères<img alt="image enchere"
					src="<%=request.getContextPath()%>/image/image_enchere.png"></h2>
	</div>
		<c:forEach var="key" items="${errModel.err}">
			<c:if test="${key == 'ErrAcc'}">
				<p style="color: red">${errModel.errMessage.get('ErrAcc')}</p>
			</c:if>
		</c:forEach>
</div>

<div class="row mb-3">
	<div class="col-3 themed-grid-col">

		<form action="AccueilServlet" method="post">
			<label for="filtre">Filtres :</label> <input type="search"
				value="Le nom de l'article contient" /> <label for="categorie">Catégorie
				:</label> <select name="categorieselect">
				<option selected>Toutes</option>
				<option>Informatique</option>
				<option>Ameublement</option>
				<option>Vêtement</option>
				<option>Sport&Loisirs</option>
			</select>

		</form>
	</div>

	<div class="col-9 themed-grid-col">
		<form action="AccueilServlet" method="post">
			<input type="submit" value="Rechercher" />
		</form>
	</div>

</div>

<table>
	<thead>
		<tr>
			<th>Image</th>
			<th>Article</th>
			<th>Montant de l'Enchere</th>
			<th>Date Enchere</th>
			<th>Vendeur</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${model.lstEnchere}" var="model">
			<tr>
				<td><img alt="image neutre"
					src="<%=request.getContextPath()%>/image/image_informatique.png">
				</td>
				<td>${model.articleConcerne.nomArticle}</td>
				<td>${model.montant_enchere}</td>
				<td>${model.dateEnchere}</td>
				<td>${model.utilisateurEncherit.pseudo}</td>
			</tr>
		</c:forEach>

	</tbody>

</table>

</body>
</html>