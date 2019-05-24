angular.
  module('index').
  factory('IndexService', ['$http', '$q', function($http, $q) {
      return {
      indexPage: function(request) {
              return $http.post('api/index/', request)
              .then(
                      function(response){
                          return response.data;
                      },
                      function(errResponse){
                          return $q.reject(errResponse);
                      }
              );
          }
      };
  }]);
