<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!empty (uploadError)}">   
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">x</button>
		<strong>Error!<br/> </strong>
		<c:if test="${uploadError == '0'}">
			Empty file<br/>
		</c:if>
		<c:if test="${uploadError == '1'}">
			Cannot create a new file<br/>
		</c:if>
		<c:if test="${uploadError == '2'}">
			Cannot overwrite a target file<br/>
		</c:if>
		<c:if test="${uploadError == '3'}">
			Not allowed file extension<br/>
		</c:if>
	</div>
</c:if>