'use strict';
// Foundation JavaScript
// Documentation can be found at: http://foundation.zurb.com/docs
$(document).foundation();

// AngularJS Code
var outdoorApp = angular.module('outdoorApp', ['ngRoute', 'usersControllers', 'usersServices', 'tokenServices', 'newsControllers', 'newsServices', 'activityControllers', 'activityServices', 'globalServices']);


outdoorApp.factory('globalResponseInterceptor',['$q','$location','$rootScope',function($q,$location,$rootScope){
    return {
        'responseError': function(rejection) {
            // do something on error
            if (rejection.status === 401) {
                $location.path('#/users/auth');
                return rejection;
            }
            return $q.reject(rejection);
        },
        'request': function(config) {
            if($rootScope.token) {
                config.headers.Authorization = 'Bearer ' + $rootScope.token;
            }
            return config || $q.when(config);
        }

    };
}]);

outdoorApp.config([
    '$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/users/auth', {
                templateUrl: 'partial/auth.html',
                //controller: 'SignUpCtrl'
            })
            .when('/news', {
                templateUrl: 'partial/list-news.html'
            })
            .when('/home', {
                templateUrl: 'partial/home.html'
            })
            .when('/activity', {
                templateUrl: 'partial/activity.html',
                controller: 'ActivityListCtrl'
            })
            .when('/activity/create/new/', {
                templateUrl: 'partial/activity-form.html',
                controller: 'AddActivityCtrl'
            })
            .otherwise({
                'redirectTo': '/home'
            });
    }
]);

outdoorApp.config([
    '$httpProvider',function($httpProvider) {
        $httpProvider.interceptors.push('globalResponseInterceptor');
    }
]);


