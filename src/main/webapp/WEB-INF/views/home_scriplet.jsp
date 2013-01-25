<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="se.persandstrom.bos.external.ApiController"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<%@taglib
	uri="http://www.springframework.org/tags/form"
	prefix="f"%>
<%@ page session="false"%>
<%@ page import="java.util.List"%>
<%@ page import="se.persandstrom.bos.external.ApiController"%>
<%@ page import="se.persandstrom.bos.internal.api.BosApi"%>
<%@ page import="se.persandstrom.bos.internal.api.Entry"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>My first web page</title>
<link
	rel="stylesheet"
	type="text/css"
	href="resources/css/TestCss.css" />
</head>
<body>
	<div id="title">
		<h1>BÖS</h1>
	</div>
	<div id="non-title">
		<div id="nav">
			<p>navigation</p>
			<p>item 1</p>
			<p>item 2</p>
		</div>
		<div id="content">
			<div id="input-area">
				<p>
				<h2>Welcome to Bös!</h2>
				</p>
				<form
					id="entry"
					action="text"
					method="post">
					<label for="majs">The best way to share text on the "internet"</label>
					<br />
					<textarea
						name="content"
						class="styled"></textarea>
					<br />
					<input
						type="submit"
						name="form"
						value="Save content" />
				</form>
			</div>
			<div class="latest">
				<p>here be the latest!!</p>
				<%
					ApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
					BosApi bosApi = (BosApi) context.getBean("BosApi");

					List<Entry> entryList = bosApi.getLatest();
					for (Entry entry : entryList) {
				%>
				<p>
					<%=entry.getContent()%>
				</p>
				<%
					}
				%>
			</div>
			<div id="extra">
				<p>
					<a href="http://www.linkedin.com/pub/per-sandstr%C3%B6m/17/5b5/553">My LinkedIn</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>