<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">  
<jsp:param name="titre" value="login" />  
</jsp:include>

<jsp:include page="navbar.jsp">  
<jsp:param name="hidden" value="true" />  
</jsp:include>

<h1>Vous avez remporté la vente</h1>

<div><img alt="image neutre" src="<%=request.getContextPath()%>/image/image_informatique.png"></div>

<div></div>

<jsp:include page="footer.jsp"/>