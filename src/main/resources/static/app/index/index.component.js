'use strict';

angular.
  module('index').
  component('index', {
    templateUrl: 'app/index/index.template.html',
    controller: ['IndexService', '$state', '$stateParams',  function(IndexService, $state, $stateParams) {
        var self = this;

        self.url = $stateParams.q;
        self.scanDepth = $stateParams.scanDepth;

        self.showErrorBlock = false;
        self.showMessageBlock = false;

        self.errors = [];

        self.indexPage = function() {
              IndexService.indexPage({url: self.url, scanDepth: self.scanDepth})
                  .then( function(response) {
                    self.showMessageBlock = true;
                  },
                  function(errResponse){
                    self.showErrorBlock = true;
                    self.errors = errResponse.data.errors;
                  }
              );
        };

        self.indexPage();
    }]
  });