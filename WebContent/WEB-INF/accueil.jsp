<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="accueil" />  
</jsp:include>

<jsp:include page="navbar.jsp" />

<h2>Liste des enchères</h2>
<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrAcc'}">
		<p style="color: red">${errModel.errMessage.get('ErrAcc')}</p>
	</c:if>
</c:forEach>

<form action="AccueilServlet" method="post">
	<label for="filtre">Filtre</label> <input type="search"
		value="recherche" placeholder="Le nom de l'article contient" /> <label
		for="categorie">Catégorie :</label> <select name="categorieselect">
		<option selected>"Toutes"</option>
		<option>"Informatique"</option>
		<option>"Ameublement"</option>
		<option>"Vêtement"</option>
		<option>"Sport&Loisirs"</option>
	</select> <input type="submit" value="Rechercher" />
</form>
<table>
	<thead>
		<tr>
			<th>Article</th>
			<th>Montant de l'Enchere</th>
			<th>Date Enchere</th>
			<th>Utilisateur</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${model.lstEnchere}" var="model">
			<tr>
				<td>${model.articleConcerne.nomArticle}</td>
				<td>${model.montant_enchere}</td> 
				<td>${model.utilisateurEncherit.nom}</td> 
				<td>${model.dateEnchere}</td>
			</tr>
		</c:forEach>

	</tbody>

</table>
<!-- TODO : insérer les images -->
<jsp:include page="footer.jsp" />
</body>
</html>