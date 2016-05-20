<html>
<head>
    <%@ page isELIgnored="false" %>
    <title>List</title>
    <link href='https://fonts.googleapis.com/css?family=Quicksand' rel='stylesheet' type='text/css'>

    <link href="resources/css/custom/phrases.css" rel="stylesheet">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/bootstrap-theme.css" rel="stylesheet">
    <link href="resources/css/bootstrap-theme.min.css" rel="stylesheet">

    <script src="resources/js/angular/angular.min.js"></script>
    <script src="resources/js/angular/angular-resource.js"></script>

    <script src="resources/js/jquary/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="resources/js/bootstrap/bootstrap.min.js"></script>
    <script src="resources/js/App.js"></script>
    <script src="resources/js/controllers/tableController.js"></script>
    <script src="resources/js/controllers/UserController.js"></script>
    <script src="resources/js/PhrasesManager.js"></script>
</head>
<body ng-app="Phrases">

<nav class="navbar navbar-default">
    <div class="container-fluid phrase-navbar">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand phrase-navbar-link" href="#">PhraseList</a>
        </div>
        <div ng-controller="userController">
            <div id="user-section" class="nav navbar-nav navbar-right login-section-hide">
                {{ name }} {{lastName}}
            </div>
            <ul id="login-section" class="nav navbar-nav navbar-right">
                <li>
                    <a  data-toggle="modal" data-target="#signUp">
                        Sign Up
                    </a>
                </li>
                <li>
                    <a  data-toggle="modal" data-target="#logIn">
                        Log In
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle  phrase-navbar-link" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
            <!-- Modal -->
            <div class="modal fade" id="logIn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Sign In</h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group">
                                    <label for="exampleInputLogin1">Login</label>
                                    <input type="text" class="form-control" id="exampleInputLogin1" ng-model="signInLogin" placeholder="Login">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" class="form-control" id="exampleInputPassword1" ng-model="signInPass" placeholder="Password">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" ng-click="getUser()">Log In</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="signUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel1">Registration</h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="col-md-12">
                                    <div class="form-group col-md-6">
                                        <label for="inputName1">Name</label>
                                        <input type="text" class="form-control" id="inputName1" ng-model="signUpName" placeholder="Name">
                                        <div class="error-box"><p class="bg-danger error-hidden">More than 2 symbols</p></div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="inputLastname1">Lastname</label>
                                        <input type="text" class="form-control" id="inputLastname1" ng-model="signUpLastname" placeholder="Lastname">
                                        <div class="error-box"><p class="bg-danger error-hidden">More than 2 symbols</p></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputLogin1">Login</label>
                                    <input type="text" class="form-control" id="inputLogin1" ng-model="signUpLogin" placeholder="Login">
                                    <div class="error-box"><p class="bg-danger error-hidden">More than 2 symbols, and should be unique</p></div>
                                </div>
                                <div class="form-group">
                                    <label for="inputEmail1">Email address</label>
                                    <input type="email" class="form-control" id="inputEmail1" ng-model="signUpEmail" placeholder="Email">
                                    <div class="error-box"><p class="bg-danger error-hidden">Seems like it is not email address</p></div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPassword1">Password</label>
                                    <input type="password" class="form-control" id="inputPassword1" ng-model="signUpPassword" placeholder="Password">
                                    <div class="error-box"><p class="bg-danger error-hidden">More than 8 symbols</p></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" ng-click="addUser()">Sign Up</button>
                        </div>
                    </div>
                </div>
            </div>

        </div>  <!-- End of user controller scope -->
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

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
            <table class="table table-hover item col-xs-11">
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
