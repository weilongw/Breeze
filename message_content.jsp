<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<c:forEach var="inboxMsg" items="${inbox}">
		<input type="hidden" id="${inboxMsg.id}user" value="${inboxMsg.sender.userName}">
		<input type="hidden" id="${inboxMsg.id}title" value="${inboxMsg.title}">
		<input type="hidden" id="${inboxMsg.id}content" value="${inboxMsg.content}">
	</c:forEach>
	<c:forEach var="sentMsg" items="${sent}">
		<input type="hidden" id="${sentMsg.id}user" value="${sentMsg.receiver.userName}">
		<input type="hidden" id="${sentMsg.id}title" value="${sentMsg.title}">
		<input type="hidden" id="${sentMsg.id}content" value="${sentMsg.content}">
	</c:forEach>
</div>