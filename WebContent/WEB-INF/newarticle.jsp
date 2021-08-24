<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="newarticle" />
</jsp:include>
<jsp:include page="navbar.jsp" />

<c:forEach var="key" items="${errModel.err}">		
	<c:if test="${key == 'ErrNewVte'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrNewVte')}">
			<p style="color:red">${erreur}</p>
		</c:forEach>	
	</c:if>		
</c:forEach>

<div class="row justify-content-start">
	<div class="col">
		<div class="image">
			<img alt="enchère new vente"
				src="<%=request.getContextPath()%>/image/image_enchere.png">
		</div>
	</div>

	<div class="col">
		<div>

			<h2>Nouvelle vente</h2>

		</div>

		<form action="NewArticleServlet" method="post">
			<p>
				<label for="article">Article :</label><input id="article"
					name="nomarticle" type="text" 
					value="${newArtModel.articleVendu.nomArticle}">
			</p>

			<p>
				<label for="description">Description :</label>

				<textarea id="description" name="description" rows="4" cols="50">
				${newArtModel.articleVendu.description}
				</textarea>

			</p>

			<p>
				<label for="categorieSelect">Catégorie :</label> 
				<select name="categorieSelect">
					 <option value="Informatique">Informatique</option>	
					 <option value="Ameublement">Ameublement</option>
					 <option value="Vêtement">Vêtement</option>
					 <option value="Sport&Loisir">Sport&Loisir</option>
				</select>
			</p>

			<p>
				<label for="photoarticle">Photo de l'article :</label> <input
					id="photoarticle" name="photoarticle" type="file">
			<!-- TODO insérer photo dans article -->
			</p>

			<p>
				<label for="miseaprix">Mise à prix :</label> <input type="number"
					id="miseaprix" name="sprix" min="0" placeholder="150"
					value="${newArtModel.articleVendu.miseAPrix}">
					
			</p>

			<p>
				<label for="datedebutencheres">Début de l'enchère :</label> <input
					type="date" id="datedebutencheres" name="datedebutencheres"
					value="${newArtModel.articleVendu.dateDebutEncheres}">
			</p>

			<p>
				<label for="datefinencheres">Fin de l'enchère :</label> <input
					type="date" id="datefinencheres" name="datefinencheres"
					value="${newArtModel.articleVendu.dateFinEncheres}" 
					>
			</p>

			<fieldset style="border: 1 px solid silver">
				<legend>Retrait</legend>
				<p>
					<label for="rue">Rue :</label> <input type="text" id="rue"
						name="rue" placeholder="Rue des Mouettes"
						value = "${newArtModel.articleVendu.retrait.rue}">
						
				</p>

				<p>
					<label for="cp">Code postal :</label> <input type="text" id="cp"
						name="code_postal" placeholder="44800"
				value = "${newArtModel.articleVendu.retrait.code_postal}"
					>
				</p>

				<p>
					<label for="ville">Saint Herblain:</label> <input type="text"
						id="ville" name="ville" placeholder="Saint Herblain"
						value = "${newArtModel.articleVendu.retrait.ville}"
						>
				</p>
			</fieldset>

			<p>
				<button type="submit" name="enregistrer" value="enregistrer">
					Enregistrer</button>
				<button type="submit" name="annuler" value="annuler">
					Annuler</button>

			</p>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp" />