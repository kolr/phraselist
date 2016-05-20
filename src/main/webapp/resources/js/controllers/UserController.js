/**
 * 20.04.2016
 * Created by Rodion.
 */
app.factory("User", function ($resource) {
    return $resource("user");
});

app.controller('userController', ['$scope', '$http', "User", function ($scope, $http, User) {
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
        console.log(user);
        user.$save("/user", function () {
            $scope.signUpLogin = "";
            $scope.signUpEmail = "";
            $scope.signUpName = "";
            $scope.signUpLastname = "";
            $scope.signUpPassword = "";
        });

    };

    $scope.getUser = function () {
        var user;
        $http.post("/user/" + $scope.signInLogin, $scope.signInPass).success(function(data){
            $('#logIn').modal('hide');
            console.log("user");
            console.log(data);
            user = data;
            initializeScopeVariables(user);
            displayUser(user);
        });
    };

    function displayUser(user) {
        if(user != undefined) {
            var list = document.getElementById("login-section");
            list.classList.add("login-section-hide");

            var userName = document.getElementById("user-section");
            userName.classList.remove("login-section-hide");
        }
    }

    function initializeScopeVariables(user) {
        if(user != undefined) {
            $scope.name = user.name;
            $scope.lastName = user.lastname;
            $scope.email = user.email;
            $scope.login = user.login;
        }
    }

}]);
