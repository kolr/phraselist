<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>List</title>
    <link href="../../resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resources/css/bootstrap.css" rel="stylesheet">
    <link href="../../resources/css/bootstrap-theme.css" rel="stylesheet">
    <link href="../../resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="../../resources/css/custom/phrases.css" rel="stylesheet">
    <script src="../../resources/js/PhrasesManager.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="list col-xs-6">
        <form id="phrases" action="phrases" method="delete">
            <c:forEach var="word" items="${lst}">
                <div id="${word.foreign}" class="item col-xs-11">
                    <div class="col-xs-1"><input type="checkbox" id="word" name="word" value="${word.foreign}"
                                                 onclick="mark(event)"></div>
                    <div class="col-xs-2">${word.foreign}</div>
                    <div class="col-xs-8">${word.translation}</div>
                    <div class="col-xs-1"><span class="glyphicon glyphicon-trash" aria-hidden="true"
                                                onclick="removeItem('${word.foreign}')"></span></div>
                </div>
            </c:forEach>
        </form>
    </div>

    <div class="item-manager col-xs-5">
        <form action="phrases" method="post">
            <input type="text" name="foreign" id="foreign">
            <input type="text" name="translation" id="translation">
            <input type="submit">
        </form>
        <span id="trash" class="glyphicon glyphicon-trash hidden" aria-hidden="true" onclick="removeItems()"></span>
    </div>
</div>
<h3>${errorMessage}</h3>
</body>
</html>
