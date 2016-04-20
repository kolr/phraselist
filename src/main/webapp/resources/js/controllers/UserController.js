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
        $http.post("/user", user, function(){
            $scope.signUpLogin = "";
            $scope.signUpEmail = "";
            $scope.signUpName = "";
            $scope.signUpLastname = "";
            $scope.signUpPassword = "";
        })

    };

    $scope.deleteWord = function (id) {
        $http.delete("/" + $scope.language + "/phrases/" + id, function () {
            console.log(id + " was delete");
        }).then(updateOnDelete);
    };

    $scope.addLabel = function (label) {
        $http.post("/" + $scope.language + "/phrases/label/" + label, function () {
            console.log(label + " was delete");
        }).then(update);
    };

    $scope.deleteWords = function () {
        if (markedCounter == 0) {
            return;
        }
        markedCounter = 0;
        $http.post("/" + $scope.language + "/phrases/all", markedItems, function () {
            console.log("several deleted.");
        }).then(update);
    }
}]);
