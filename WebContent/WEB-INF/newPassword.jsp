<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param name="titre" value="nouveau mot de passe" />
</jsp:include>

<jsp:include page="navbar.jsp">
	<jsp:param name="hidden" value="true" />
</jsp:include>


<c:forEach var="key" items="${errModel.err}">
	<c:if test="${key == 'ErrMod'}">
		<c:forEach var="erreur" items="${errModel.errMessages.get('ErrMod')}">
			<p style="color: red">${erreur}</p>
		</c:forEach>
	</c:if>
</c:forEach>


<div id="login">
	<div class="container">
		<form class="form" action="NewPasswordServlet" method="post">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-20">

						<div id="login-form">
							<h3 class="text-center text-info">Nouveau mot de passe</h3>
							<div class="form-group">
								<label for="mail" class="text-info">Adresse mail :</label><br>
								<input type="text" name="mail" required="required" id="mail"
									class="form-control">
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Nouveau mot de
									passe :</label><br> <input type="password" name="password"
									required="required" id="password" class="form-control">
							</div>
							<div class="form-group">
								<label for="confirmation" class="text-info">Confirmation
									:</label><br> <input type="password" name="confirmation"
									required="required" id="confirmation" class="form-control">
							</div>
						</div>
						<div id="register-link" class="text-right">
							<button class="btn-secondary btn-lg" type="submit"
								name="formulaireNewPassword" value="envoyer">Envoyer</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp" />