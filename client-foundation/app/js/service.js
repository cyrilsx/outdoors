'use strict';
var tokenServices = angular.module('tokenServices', ['ngResource']);
var usersServices = angular.module('usersServices', ['ngResource']);
var newsServices = angular.module('newsServices', ['ngResource']);


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
            create: {method: 'PUT', params: {username: ''}},
            delete: {method: 'DELETE', params: {username: '@username'}}
        });
    }
]);


newsServices.factory('News', [ '$resource',
    function ($resource) {
        return $resource('services/news/:news_id', {}, {
            query: {method: 'GET', params: {news_id: ''}, isArray: true},
            post: {method: 'POST'},
            create: {method: 'PUT', params: {news_id: ''}},
            delete: {method: 'DELETE', params: {news_id: '@news_id'}}
        });
    }
]);
