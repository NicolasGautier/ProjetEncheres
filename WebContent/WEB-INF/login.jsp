<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param name="titre" value="login" />
</jsp:include>

<jsp:include page="navbar.jsp">
	<jsp:param name="hidden" value="true" />
</jsp:include>


<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrLog'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrLog')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>


<div id="login">
	<div class="container">
		<div id="login-row"
			class="row justify-content-center align-items-center">
			<div id="login-column" class="col-md-6">
				<div id="login-box" class="col-md-12">
					<form id="login-form" class="form" action="LoginServlet"
						method="post">
						<h3 class="text-center text-info">Page de connexion</h3>
						<div class="form-group">
							<label for="identifiant" class="text-info">Identifiant :</label><br>
							<input type="text" name="identifiant" required="required"
								id="username" class="form-control">
						</div>
						<div class="form-group">
							<label for="password" class="text-info">Mot de Passe :</label><br>
							<input type="password" name="password" required="required"
								id="password" class="form-control">
						</div>
						<div class="form-group">
							<label for="remember-me" class="text-info"><span>Se
									souvenir de moi</span> <span><input id="remember-me"
									name="checkRememberMe" type="checkbox"></span></label><br>
							<a href="NewPasswordServlet">Mot de passe oublié</a>
							<a href="InsererProfilServlet" class="creercompte">Créer un compte</a>
																			
							</div>
						<div id="register-link" class="text-right">
							<button class="btn-secondary btn-lg" type="submit" name="formulaireLogin" value="Connexion">Connexion</button>													
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />

