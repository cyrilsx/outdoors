'use strict';
var tokenServices = angular.module('tokenServices', ['ngResource']);
var usersServices = angular.module('usersServices', ['ngResource']);


tokenServices.factory('Token', [ '$resource',
    function ($resource) {
        return $resource('services/oauth/token', {}, {
            auth: {method: 'POST',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}},
        });
    }
]);


usersServices.factory('User', [ '$resource',
    function ($resource) {
        return $resource('services/user/:username', {}, {
            query: {method: 'GET', params: {username: ''}, isArray: true},
            post: {method: 'POST'},
            create: {method: 'PUT'},
            delete: {method: 'DELETE', params: {username: '@username'}}
        });
    }
]);
