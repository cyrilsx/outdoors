'use strict';
var tokenServices = angular.module('tokenServices', ['ngResource']);
var usersServices = angular.module('usersServices', ['ngResource']);
var newsServices = angular.module('newsServices', ['ngResource']);
var activityServices = angular.module('activityServices', ['ngResource']);
var globalServices = angular.module('globalServices', []);


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


activityServices.factory('Activity', [ '$resource',
    function ($resource) {
        return $resource('services/activity/:name', {}, {
            query: {method: 'GET', params: {name: ''}, isArray: true},
            get: {method: 'GET', params: {name: '@name'}},
            post: {method: 'POST'},
            create: {method: 'PUT', params: {name: ''}},
            delete: {method: 'DELETE', params: {name: '@name'}}
        });
    }
]);


globalServices.factory('AppVariable', function() {
    return {
        username: 'Anonymous',
        event_type: {
            update_news : 'UPDATE_NEWS',
            login_successful : 'LOGIN_SUCESSFUL'
        },
        anonymous_user: 'Anonymous',
        is_anonymous: function() {
            return this.username === this.anonymous_user;
        }

    };

});
