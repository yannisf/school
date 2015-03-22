'use strict';

angular.module('foundation', ['ngRoute', 'ngResource', 'ui.bootstrap', 'child'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/foundation/list', {
                templateUrl: 'application/foundation/list.html',
                controller: 'foundationController'
            })
            .when('/group/:groupId', {
                templateUrl: 'application/foundation/group.html',
                controller: 'listGroupController'
            });
    }])

    .factory('Foundation', ['$resource', function($resource) {
        return $resource('api/foundation', { }, {
            system: {method: 'GET', isArray: true},
        });
    }])

    .service('FoundationService', ['Foundation', 'Group', function (Foundation, Group) {
		var school = { id: null, name: null };
		var department = { id: null, name: null };
		var group = { id: null, name: null };
		var initializeGroup = function(groupId) {
			group.id = groupId;
			var groupInfo = Group.info({id: groupId});
			groupInfo.$promise.then(function(response) {
				school.name = response.school;
				department.name = response.department;
				group.name = response.group;
			});
		};
		var groupChildren = function() {
			var self = this;
			var children = Group.children({id: group.id});
			children.$promise.then(function(response) {
				self.groupChildrenIds = response.map(function (child) {
				   return child.id;
				});
			});

			return children;
		};
		var groupChildrenIds = [];
		var fetchSystem = function() {
			return Foundation.system();
		};
		var reset = function() {
			this.school.id = null;
			this.school.name = null;
			this.department.id = null;
			this.department.name = null;
			this.group.id = null;
			this.group.name = null;
			this.groupChildrenIds = [];
		};

		return {
			school: school,
			department: department,
			group: group,
			reset: reset,
			initializeGroup: initializeGroup,
			fetchSystem: fetchSystem,
			groupChildren: groupChildren,
			groupChildrenIds: groupChildrenIds
		};
    }])

    .controller('foundationController', ['$scope', 'FoundationService',
        function ($scope, FoundationService) {
            angular.extend($scope, {
                data: {
                    schools: FoundationService.fetchSystem()
                }
            });
        }
    ])

    .controller('listGroupController', ['$scope', 'FoundationService', 'ChildService',
        function ($scope, FoundationService, ChildService) {
           angular.extend($scope, {
               data: {
                   children: FoundationService.groupChildren()
               },
               viewData: {
					groupId: FoundationService.group.id,
                   	noChildren: false
               }
           });

			$scope.data.children.$promise.then(function(response) {
				$scope.viewData.noChildren = response.length === 0;
			});

           	$scope.goToChild = function ($event) {
				var clickedElement = angular.element($event.target);
               	var childId = clickedElement.scope().child.id;
               	$scope.go('/child/' + childId + '/view', $event);
           	};
       	}
	]);

