<jsp:include page="header.jsp">  
<jsp:param name="titre" value="accueil" />  
</jsp:include>

<jsp:include page="navbar.jsp"/>

	<h2>Liste des enchères</h2>
	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrLog'}">
		<p style="color:red">${errModel.errMessage.get('ErrLog')}</p>
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
	<c:forEach items="${model.lst}" var="enchere">
		<p>${enchere.article.description}/ ${enchere.article.prix} / ${enchere.datefin} /
			${enchere.vendeur}</p>
	</c:forEach>
<!-- TODO : insérer les images -->

</body>
</html>