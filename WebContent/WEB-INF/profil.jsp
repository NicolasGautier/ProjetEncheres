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
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
	<c:if test="${key == 'ErrGet'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrGet')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>

<div class="container-fluid">
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">


			<h2 class="card-text-center">MON PROFIL</h2>


			<div class="container-fluid">

				<div class="card" style="width: 30rem;">
					<div class="card-body">

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Pseudo :</div>
							<div class="col-5">${profModel.utilisateur.pseudo}</div>
							<div class="col-1"></div>

						</div>
						<div class="divvide"></div>
						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Nom :</div>
							<div class="col-5">${profModel.utilisateur.nom}</div>
							<div class="col-1"></div>
						</div>

						<div class="divvide"></div>
						<div class="divvide"></div>

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Pr�nom :</div>
							<div class="col-5">${profModel.utilisateur.prenom}</div>
							<div class="col-1"></div>
						</div>

						<div class="divvide"></div>
						<div class="divvide"></div>

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Email :</div>
							<div class="col-5">${profModel.utilisateur.email}</div>
							<div class="col-1"></div>
						</div>

						<div class="divvide"></div>
						<div class="divvide"></div>

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">T�l�phone :</div>
							<div class="col-5">${profModel.utilisateur.telephone}</div>
							<div class="col-1"></div>
						</div>

						<div class="divvide"></div>
						<div class="divvide"></div>

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Rue :</div>
							<div class="col-5">${profModel.utilisateur.rue}</div>
							<div class="col-1"></div>
						</div>
						<div class="divvide"></div>
						<div class="divvide"></div>

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Code Postal :</div>
							<div class="col-5">${profModel.utilisateur.codePostal}</div>
							<div class="col-1"></div>
						</div>
						<div class="divvide"></div>
						<div class="divvide"></div>

						<div class="row">
							<div class="col-1"></div>
							<div class="col-5">Ville :</div>
							<div class="col-5">${profModel.utilisateur.ville}</div>
							<div class="col-1"></div>
						</div>
					</div>


					<c:if
						test="${profModel.utilisateur.noUtilisateur == logModel.utilisateur.noUtilisateur}">
						<div class="row">
						<div class="col-1"></div>
						<div class="col-10"><a class="btn btn-secondary btn-lg btn-block" href="ModifierProfilServlet">Modifier</a></div>
						<div class="col-1"></div>
						</div>
					</c:if>

				</div>


			</div>
			<div class="col-4"></div>



		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />




