'use strict';

angular.module('schoolApp')

    .directive('inputAddress', ['ChildService', 'Address', 'addressService', 'uuid4',
        function (ChildService, Address, addressService, uuid4) {
            return {
                restrict: 'E',
                scope: {
                    address: "=",
                    shareOption: "=",
                    typeaheads: "="
                },
                templateUrl: "address/input-address.tpl.html",
                link: function (scope) {

                    scope.viewData = {
                        commonAddress: false,
                        isAddressOpen: false
                    };

                    if (scope.shareOption) {
                        var originalAddressId;
                        var originalCommonAddress = false;

                        var unwatch = scope.$watch('address.id', function (newval) {
                            if (newval) {
                                originalAddressId = newval;
                                originalCommonAddress = (newval == ChildService.child.addressId);
                                scope.viewData.commonAddress = originalCommonAddress;
                                unwatch();
                            }
                        });

                        scope.$watch('viewData.commonAddress', function (newval) {
                            if (newval && originalAddressId) {
                                scope.address = Address.get({addressId: ChildService.child.addressId});
                            } else if (originalCommonAddress) {
                                scope.address = {
                                    id: uuid4.generate()
                                };
                            } else if (originalAddressId) {
                                if (addressService.isBlank(scope.address)) {
                                    scope.address = {
                                        id: originalAddressId
                                    };
                                } else {
                                    scope.address = Address.get({addressId: originalAddressId});
                                }
                            }
                        });
                    }
                }
            };
        }]);

