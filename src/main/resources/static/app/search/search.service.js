angular.
  module('search').
  factory('SearchService', ['$http', '$q', function($http, $q) {
      return {
      search: function(request) {
              return $http.post('api/search/', request)
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
