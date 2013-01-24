<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<strong>Post a Message</strong>
	<f:form method="post" action="text" commandName="entry">

		<f:label path="content">Content</f:label>
		<br/>
		<f:input path="content" />
		<br/>

		<f:label path="createdTimeMs">createdTimeMs</f:label>
		<br/>
		<f:input path="createdTimeMs" />

		<input type="submit" name="form" value="Save content" />
	</f:form>

</body>
</html>
