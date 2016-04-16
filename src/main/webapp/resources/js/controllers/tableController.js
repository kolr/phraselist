app.factory("Word", function($resource){
  return $resource(":language/phrases", {language: '@language'});
})

app.controller('TableController', ['$scope', '$http', "Word", function($scope, $http, Word) {
  $scope.language = 'english';
  $scope.words = [];
  // debugger
  var url = function() {
    return {language: $scope.language||'english'};
  }

  var update = function(res) {
    if($scope.words.length == 0 || res.data == "") {
      $scope.words = Word.query(url());
    } else {
      $scope.words.push(res);
    }
  };

  var updateOnDelete = function(res) {
      if ($scope.words.length == 0 || res.data == "") {
          $scope.words = Word.query(url());
      } else {
          $scope.words.push(res);
      }
  };

  update();
  $scope.addWord = function() {
    var word = new Word();
    word.foreign = $scope.foreign;
    word.translation = $scope.translation;
    word.$save(url(), function() {
      $scope.foreign = "";
      $scope.translation = "";
    }).then(update);

  };

  $scope.deleteWord = function(id) {
      $http.delete("/" + $scope.language + "/phrases/" + id, function(){
          console.log(id + " was delete");
      }).then(update);
  };
}]);
