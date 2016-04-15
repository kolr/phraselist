<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>List</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/bootstrap-theme.css" rel="stylesheet">
    <link href="resources/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="resources/css/custom/phrases.css" rel="stylesheet">

    <script src="resources/js/angular/angular.min.js"></script>
    <script src="resources/js/angular/angular-resource.js"></script>

    <script src="resources/js/App.js"></script>
    <script src="resources/js/controllers/tableController.js"></script>

    <script src="resources/js/PhrasesManager.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body ng-app="Phrases">
<div class="container-fluid" ng-controller="TableController">
    <div class="list col-xs-6">
        <form id="phrases" action="phrases" method="delete">
            <c:forEach var="word" items="${lst}">
                <div class="item col-xs-11" ng-repeat="word in words">

                    <div class="col-xs-1"><input type="checkbox" id="word" name="word" value="${word.foreign}"
                                                 onclick="mark(event)"></div>
                    <div class="col-xs-2">{{word.foreign}}</div>
                    <div class="col-xs-8">{{word.translation}}</div>
                    <div class="col-xs-1"><span class="glyphicon glyphicon-trash" aria-hidden="true"
                                                onclick="removeItem('${word.foreign}')"></span></div>
                </div>
            </c:forEach>
        </form>
    </div>

    <div class="item-manager col-xs-5">
        <form>
            <div class="">
                <input type="text" class="form-control lg-input add-word-text-area" id="originalWord"
                       placeholder="Original Word" ng-model="foreign">
            </div>
            <div class="">
                <input type="text" class="form-control lg-input add-word-text-area" id="translation"
                       placeholder="Translation" ng-model="translation">
            </div>
            <div id="add-word-button" ng-click="addWord()">
                <span class="glyphicon glyphicon-ok vocabulary-button-text" aria-hidden="true"></span>
            </div>
        </form>
        <span id="trash" class="glyphicon glyphicon-trash hidden" aria-hidden="true" onclick="removeItems()"></span>
    </div>
</div>
</body>
</html>
