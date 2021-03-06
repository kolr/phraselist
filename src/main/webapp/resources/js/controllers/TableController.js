app.factory("Word", function ($resource) {
    return $resource(":language/phrases", {language: '@language'});
});

app.controller('TableController', ['$scope', '$http', "Word", "$rootScope", function ($scope, $http, Word, $rootScope) {
    $scope.language = 'english';
    $scope.words = [];
    var url = function () {
        return {language: $scope.language || 'english'};
    };

    $rootScope.$on("CallUpdateAfterLogin", function () {
        $scope.updateAfterLogin();
    })

    $rootScope.$on("Update", function () {
        update();
    })

    $scope.updateAfterLogin = function () {
        $scope.words = Word.query(url());
        enablingDeleteButton();
        displayEnabling();
    };

    $scope.searchThroughList = function () {
        var searchKey = $scope.searchInput;
        $http.get("/" + $scope.language + "/phrases/" + searchKey).success(function (data) {
            if (data.length == 0) {
                document.getElementById("phraselist-error-holder").classList.remove("error-hidden");
            } else {
                document.getElementById("phraselist-error-holder").classList.add("error-hidden");
            }
            $scope.words = data;
        }).error(function () {
            console.log("There is no such word in your list.");
        })

    };

    var update = function (res) {
        if (res == undefined) {
            $scope.words = Word.query(url());
        } else if ($scope.words.length == 0 || res.data == "") {
            $scope.words = Word.query(url());
        } else {
            $scope.words.push(res);
        }
        enablingDeleteButton();
        displayEnabling();
    };


    var updateOnDelete = function (res) {
        var deletedID = res.data;
        var el = $scope.words.find(function (arrItem) {
            return arrItem.id === deletedID;
        });
        var deletedIndex = $scope.words.indexOf(el);
        var firstPart = $scope.words.slice(0, deletedIndex);
        var secondPart = $scope.words.slice(deletedIndex + 1);
        for (var i = 0; i < secondPart.length; i++) {
            firstPart.push(secondPart[i])
        }
        $scope.words = firstPart;
    };

    update();
    $scope.addWord = function () {
        var word = new Word();
        word.foreign = $scope.foreign;
        word.translation = $scope.translation;
        word.$save(url(), function (data) {
            $scope.foreign = "";
            $scope.translation = "";
        }, function () {
            var errBox = document.getElementById("add-word-button-error-box");
            errBox.classList.remove("hidden");
            setTimeout(function () {
                errBox.classList.add("hidden");
            }, 3000);
        }).then(update);

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
