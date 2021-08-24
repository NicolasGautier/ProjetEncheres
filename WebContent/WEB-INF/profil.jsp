<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="profil" />  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

	<c:forEach var="key" items="${errModel.err}">
		<c:if test="${key == 'ErrPar'}">
			<p style="color:red">${errModel.errMessage.get('ErrPar')}</p>
		</c:if>	
		<c:if test="${key == 'ErrGet'}">
			<p style="color:red">${errModel.errMessage.get('ErrGet')}</p>
		</c:if>	
	</c:forEach>


<div class="container-fluid">
	<div class="row">
		 <div class="col-4"> </div>
 		 <div class="col-1">Pseudo:</div>
 		 <div class="col-1"> <h2>${profModel.utilisateur.pseudo}</h2></div>
 		 <div class="col-2"> </div>
 		 <div class="col-4"> </div>
 	</div> 
 		 <div class="row"> 
 		 <div class="col-12"> </div>
 		 </div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Nom :</div>
 		 <div class="col-3">${profModel.utilisateur.nom}</div>
 		 <div class="col-4"> </div>
 	</div> 
 	<div class="row"> 
 	 <div class="col-12"> </div>
 	</div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Prénom :</div>
 		 <div class="col-3">${profModel.utilisateur.prenom}</div>
 		 <div class="col-4"> </div>
 	</div> 
 	<div class="row"> 
 	 <div class="col-12"> </div>
 	</div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Email :</div>
 		 <div class="col-3">${profModel.utilisateur.email}</div>
 		 <div class="col-4"> </div>
 	</div> 
 	<div class="row"> 
 	 <div class="col-12"> </div>
 	</div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Téléphone :</div>
 		 <div class="col-3">${profModel.utilisateur.telephone}</div>
 		 <div class="col-4"> </div>
 	</div> 
 	<div class="row"> 
 	 <div class="col-12"> </div>
 	</div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Rue :</div>
 		 <div class="col-3">${profModel.utilisateur.rue}</div>
 		 <div class="col-4"> </div>
 	</div> 
 	<div class="row"> 
 	 <div class="col-12"> </div>
 	</div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Code Postal :</div>
 		 <div class="col-3">${profModel.utilisateur.codePostal}</div>
 		 <div class="col-4"> </div>
 	</div> 
 	<div class="row"> 
 	<div class="col-12"> </div>
 	</div> 
 	<div class="row"> 
 		 <div class="col-4"> </div>
 		 <div class="col-1">Ville :</div>
 		 <div class="col-3">${profModel.utilisateur.ville}</div>
 		 <div class="col-4"> </div>
 	</div> 
</div> 
 		 
 			
	<c:if test="${profModel.utilisateur.noUtilisateur == logModel.utilisateur.noUtilisateur}">
		<a class="btn btn-primary" href="ModifierProfilServlet">Modifier</a>
	</c:if>
	
<jsp:include page="footer.jsp"/>




