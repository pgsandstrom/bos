<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Spring MVC Forms</title>
</head>
<body>
	<h1>
		<strong>OMG HERE IS CONTENT:</strong>
	</h1>

	<c:out value="${entry.content}"></c:out>
</body>
</html>