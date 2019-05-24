'use strict';

angular.
  module('indexForm').
  component('indexForm', {
    templateUrl: 'app/index-form/index-form.template.html',
    controller: ['$state', function($state) {
        var self = this;

        self.url = '';
        self.scanDepth = '';

        self.submitClick = function() {
            $state.go('index', {q: self.url, scanDepth: self.scanDepth});
        }
  }]
});
