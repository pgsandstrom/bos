<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%><!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>BÖS</title>
<link
	rel="stylesheet"
	type="text/css"
	href="resources/css/home.css" />
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
		<div id="content-container">
			<div id="input-area-container">
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
							id="content"
							name="content"
							class="styled"></textarea>
						<br />
						<input
							type="submit"
							name="form"
							value="Save content" />
					</form>
				</div>
			</div>
			<div id="latest-container">
				<p class="latest-entry">Please check out those new entries:</p>
				<div id="latest-items">
					<c:forEach
						items="${latestList}"
						var="entry">
						<a href="text/${entry.getKey()}">
							<p class="latest-entry ellipsize">
								<c:out value="${entry.content}"></c:out>
							</p>
						</a>
					</c:forEach>
				</div>
				<button id="update">update</button>
			</div>
			<div id="extra">
				<p>
					<a href="http://www.linkedin.com/pub/per-sandstr%C3%B6m/17/5b5/553">My LinkedIn</a>
				</p>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<script
		type="text/javascript"
		src="resources/js/home.js"></script>
</body>
</html>