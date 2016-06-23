/**
 * Created by Rodion on 20.06.2016.
 */
app.factory("TableService", ['$http', function($http) {
    return {
        update: function() {
            var words = [];
            words = $http.get("");
        }
    };
}]);