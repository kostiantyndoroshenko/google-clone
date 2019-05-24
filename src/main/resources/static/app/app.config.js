'use strict';

angular.
  module('google')
  .config(function ($stateProvider, $urlRouterProvider) {
      $stateProvider
      .state('search-from', {
                     url: '/',
                     template: '<search-form></search-form>'
      })
      .state('index-form', {
                     url: '/index',
                     template: '<index-form></index-form>'
      })
      .state('index', {
                           url: '/index?q&scanDepth',
                           template: '<index></index>'
            })
      .state('search', {
                     url: '/search?q',
                     template: '<search></search>'
      });

      $urlRouterProvider.otherwise('/');
    });
