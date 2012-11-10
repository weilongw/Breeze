<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty (errors)}">   
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">x</button>
		<strong>Error!<br/> </strong>
		<c:forEach var="error" items="${errors}">
			${error}<br/>
		</c:forEach>
	</div>
</c:if>
