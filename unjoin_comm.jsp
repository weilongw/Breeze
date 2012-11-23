<?xml version="1.0" encoding="utf-8"?>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% response.setHeader("Content-Type","text/xml"); %>

<results>
	<c:forEach var="error" items="${errors}">
		<error><c:out value="${error}"/></error>
	</c:forEach>
	<success>${success}</success>
	<choice>${choice}</choice>
		<comm>${commName}</comm>
</results>