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
        $http.get("/user/" + $scope.signInLogin, function (data) {
            console.log("user");
            console.log(data);
        });

    };

}]);
