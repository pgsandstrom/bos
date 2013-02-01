<%@page pageEncoding="utf-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>BÖS text</title>
    <link
            rel="stylesheet"
            type="text/css"
            href="/resources/css/general.css"/>
    <link
            rel="stylesheet"
            type="text/css"
            href="/resources/css/text.css"/>
</head>
<body>

<%--<%@ include file="/resources/title.html" %>--%>
<div id="title">
    <a href="/">
        <img src="/resources/img/bos.png"/>
    </a>
</div>

<div class="input-area">
    <div class="input-area-content">
        HERE IS SOME CONTENT:
        <textarea class="styled"><c:out value="${entry.content}"></c:out></textarea>
    </div>
</div>


</body>
</html>