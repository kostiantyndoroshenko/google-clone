'use strict';

angular.
  module('search').
  component('search', {
    templateUrl: 'app/search/search.template.html',
    controller: ['SearchService', '$scope', '$stateParams', '$sce', function (SearchService, $scope, $stateParams, $sce) {
        var self = this;

        self.actualSortType = {value: 0, title: 'relevant'};
        self.showedSortType = {value: 1, title: 'alphabet'};

        self.phrase = $stateParams.q;

        self.refresh = function() {
            self.totalPages = -1;
            self.pageNumber = -1;

            self.pages = [];

            self.errors = [];

            self.showErrorBlock = false;
            self.showEmptyResult = false;
            self.showResultBlock = false;
        }

        self.showViewMore = function () {
            return self.pageNumber < self.totalPages - 1;
        }

        self.highlight = function(text, search) {
            if (!search) {
                return $sce.trustAsHtml(text);
            }

            var text = text.replace(new RegExp(search, 'gi'), '<span class="highlightedText">$&</span>');
            var firstSpanPosition = text.indexOf('<span');
            var startTextPosition = text.substring(0, firstSpanPosition).lastIndexOf('.');

            return $sce.trustAsHtml(text.substring(startTextPosition + 1, startTextPosition + 400));
        };

        self.loadPages = function () {
            SearchService.search({phrase: self.phrase, pageNumber: self.pageNumber + 1, sortType: self.actualSortType.value})
            .then(
                function(data) {
                    self.pages = self.pages.concat(data.content);
                    self.totalPages = data.totalPages;
                    self.pageNumber = data.pageable.pageNumber;

                    if (self.pages.length == 0) {
                        self.showEmptyResult = true;
                    } else {
                        self.showResultBlock = true;
                    }
                },
                function (data) {
                    self.errors = data.data.errors;
                    self.showErrorBlock = true;
                }
            );
        }

        self.sortClick = function() {
            [self.showedSortType, self.actualSortType] = [self.actualSortType, self.showedSortType];

            self.refresh();

            self.loadPages();
        }

        self.refresh();
        self.loadPages();
      }
    ]
  });
