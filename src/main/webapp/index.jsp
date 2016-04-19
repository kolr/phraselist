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
    <div class="col-xs-6 right">
        <div class="col-xs-12 list-manager">
            <div id="deleteAll" class="manager-button col-xs-3 disabled-button" ng-click="deleteWords()">
                <span id="trash" class="delete glyphicon glyphicon-trash" aria-hidden="true"></span>
            </div>
            <div class="col-xs-6 search-input">
                <input type="text" class="form-control" placeholder="Enter a word you want to find">
            </div>
        </div>
        <div class="list col-xs-12 word-table">
            <table class="table table-hover item col-xs-11" >
                <tr ng-repeat="word in words">
                    <td class="col-md-1"><input type="checkbox" id="word" name="word" value="${word.foreign}"
                                                 onclick="mark(event)"></td>
                    <td class="col-md-2">{{word.foreign}}</td>
                    <td class="col-md-5">{{word.translation}}</td>
                    <td class="word-id">{{word.id}}</td>
                    <td class="col-md-1"><span class="glyphicon glyphicon-trash" aria-hidden="true"
                                                ng-click="deleteWord(word.id)"></span></td>
                </tr>
            </table>
        </div>
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
            <div class="manager-button" ng-click="addWord()">
                <span class="glyphicon glyphicon-ok vocabulary-button-text" aria-hidden="true"></span>
            </div>
        </form>
        <form>
            <div class="">
                <input type="text" class="form-control lg-input add-word-text-area" id="label"
                       placeholder="Label" ng-model="label">
            </div>
            <div class="manager-button" ng-click="addLabel(label)">
                <span class="glyphicon glyphicon-ok vocabulary-button-text" aria-hidden="true"></span>
            </div>
        </form>
    </div>
</div>
</body>
</html>
