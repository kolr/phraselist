app.factory("Word", function($resource){
  return $resource(":language/phrases", {language: '@language'});
})

app.controller('TableController', ['$scope', "Word", function($scope, Word) {
  $scope.language = 'english';
  $scope.words = [];
  // debugger
  var url = function() {
    return {language: $scope.language||'english'};
  }

  var update = function(res) {
    console.log(url());
    if($scope.words.length == 0) {
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

  $scope.deleteWord = function(e) {
    var el = e.target;
    console.log(el);
    // var arr = $scope.words.splice(index, 1);
    // var word = new Word();
    // word.foreign = $scope
  };
}]);
