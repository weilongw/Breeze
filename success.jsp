<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty (success)}">   
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert">x</button>
		<strong>Success!</strong>
			${success}<br/>
	</div>
</c:if>
