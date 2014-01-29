'use strict';
var usersServices = angular.module('usersServices', ['ngResource']);


usersServices.factory('User', [ '$resource',
    function ($resource) {
        return $resource('services/oauth/token', {}, {
            auth: {method: 'POST',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}},
            create: {method: 'PUT'},
        });
    }
]);
