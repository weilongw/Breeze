<?xml version="1.0" encoding="utf-8"?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% response.setHeader("Content-Type", "text/xml"); %>

<root result="${result}">
	<c:forEach var="msg" items="${msgs}">
		<message>${msg}</message>
	</c:forEach>
	<messageID>${messageID}</messageID>
	<messageDate>${date}</messageDate>
</root>