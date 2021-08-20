<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="newarticle" />
</jsp:include>

<jsp:include page="navbar.jsp" />

<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrNewVte'}">
		<p style="color: red">${errModel.errMessage.get('ErrAcc')}</p>
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
					name="nomarticle" type="text">
			</p>

			<p>

				<label for="description">Description :</label>
				<textarea id="description" name="description" rows="4" cols="50"></textarea>
			</p>

			<p>
				<label for="categorie">Catégorie :</label> <select name="categorie">
					<option>Informatique</option>
					<option>Ameublement</option>
					<option>Vêtement</option>
					<option>Sport&Loisirs</option>
				</select>
			</p>

			<p>
				<label for="photoarticle">Photo de l'article :</label> <input
					id="photoarticle" name="photoarticle" type="file">
			</p>

			<p>
				<label for="miseaprix">Mise à prix :</label> <input type="number"
					id="miseaprix" name="miseaprix" placeholder="150">
			</p>

			<p>
				<label for="datedebutencheres">Début de l'enchère :</label> <input
					type="date" id="datedebutencheres" name="datedebutencheres">
			</p>

			<p>
				<label for="datefinencheres">Fin de l'enchère :</label> <input
					type="date" id="datefinencheres" name="datefinencheres">
			</p>

			<fieldset style="border 1 px solid silver">
				<legend>Retrait</legend>
				<p>
				<label for="rue">Rue :</label> <input type="text" id="rue"
					name="rue" placeholder="Rue des Mouettes"> 
				</p>
				
				<p>
				<label for="cp">Code postal :</label> <input type="text" id="cp" name="code_postal"
					placeholder="44800"> 
				</p>
				
				<p>
				<label for="ville">Saint
					Herblain:</label> <input type="text" id="ville" name="ville"
					placeholder="Saint Herblain">
				</p>
			</fieldset>

			<p>
				<input type="submit" value="Enregistrer"> <input
					type="submit" value="Annuler">
			</p>
		</form>
	</div>
</div>


</body>
</html>