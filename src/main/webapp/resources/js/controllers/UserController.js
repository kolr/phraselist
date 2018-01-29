/**
 * 20.04.2016
 * Created by Rodion.
 */
app.factory("User", function ($resource) {
    return $resource("user");
});

app.controller('userController', ['$scope', '$http', "User", "$rootScope", function ($scope, $http, User, $rootScope) {
    $scope.name;
    $scope.lastName;
    $scope.email;
    $scope.login;

    $scope.addUser = function () {
        var user = new User();
        user.login = $scope.signUpLogin;
        user.email = $scope.signUpEmail;
        user.name = $scope.signUpName;
        user.lastname = $scope.signUpLastname;
        user.password = $scope.signUpPassword;
        user.$save("/user", function (data) {
            $scope.signUpLogin = "";
            $scope.signUpEmail = "";
            $scope.signUpName = "";
            $scope.signUpLastname = "";
            $scope.signUpPassword = "";
            $('#signUp').modal('hide');
            user = data;
            initializeScopeVariables(user);
            displayUser(user);
            $rootScope.$emit("CallUpdateAfterLogin", {});
        });

    };

    $scope.getUser = function () {
        var user;
        $http.post("/user/" + $scope.signInLogin, $scope.signInPass).success(function (data) {
            $scope.signInLogin = "";
            $scope.signInPass = "";
            $('#logInModal').modal('hide');
            user = data;
            initializeScopeVariables(user);
            displayUser(user);
            $rootScope.$emit("CallUpdateAfterLogin", {});
        }).error(function () {
            $('#logInModal').modal('hide');
            console.log("An error has occurred.");
        })
    };

    $scope.signOut = function () {
        $http.post("/user/" + $scope.login + "/out").success(function () {
            nullify();
            displayGuestPanel();
            $rootScope.$emit("Update", {});
        }).error(function () {
            console.log("Error while signing out.");
        })
    }

    var checkUser = function () {
        var user;
        $http.get("/user").success(function (data) {
            user = data;
            initializeScopeVariables(user);
            displayUser(user);
        }).error(function () {
            console.log("There is no registered user.");
            var list = document.getElementById("login-section");
            list.classList.remove("login-section-hide");
        });
    };

    checkUser();

    function displayUser(user) {
        if (user != undefined) {
            var list = document.getElementById("login-section");
            list.classList.add("login-section-hide");

            var userName = document.getElementById("user-section");
            userName.classList.remove("login-section-hide");
        }
    }

    function initializeScopeVariables(user) {
        if (user != undefined) {
            $scope.name = user.name;
            $scope.lastName = user.lastname;
            $scope.email = user.email;
            $scope.login = user.login;
        }
    }

    function nullify() {
        $scope.name = null;
        $scope.lastName = null;
        $scope.email = null;
        $scope.login = null;
    }

    function displayGuestPanel() {
        var list = document.getElementById("login-section");
        list.classList.remove("login-section-hide");

        var userName = document.getElementById("user-section");
        userName.classList.add("login-section-hide");
    }

}]);

