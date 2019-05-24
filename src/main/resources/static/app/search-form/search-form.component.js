'use strict';

angular.
  module('searchForm').
  component('searchForm', {
    templateUrl: 'app/search-form/search-form.template.html',
    controller: ['$state', function($state) {
        var self = this;

        self.phrase = '';

        self.submitClick = function() {
            $state.go('search', {q: self.phrase});
        }
    }]
  });
