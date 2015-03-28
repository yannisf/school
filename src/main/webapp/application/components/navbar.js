'use strict';
angular.module('schoolApp').directive('navbar', ['FoundationService', 
    function (FoundationService) {
        return {
            restrict: 'E',
            replace: true,
            scope: true,
            templateUrl: "application/components/navbar.tpl.html",
            link: function(scope, element) {
                scope.group = FoundationService.group;
            },
            controller: function($scope) {
//                $scope.navbarCollapsed = true;
            }
        };
    }
]);
