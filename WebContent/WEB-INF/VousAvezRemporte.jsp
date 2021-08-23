<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="detailvente"/>  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

<h2> Vous avez remporté la vente </h2>

<h2>${nomarticle.} </h2>
<h2>Description</h2>
<h2>Meilleure offre : </h2>
<h2>Mise à prix</h2>
<h2>Retrait</h2>
<h2>Vendeur</h2>
<h2>Tel</h2>

     
<button type="submit" id="back" name="back" value="back"> Back</button>


<jsp:include page="footer.jsp"/> 