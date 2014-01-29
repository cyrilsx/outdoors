'use strict';
// Foundation JavaScript
// Documentation can be found at: http://foundation.zurb.com/docs
$(document).foundation();

// AngularJS Code
var outdoorApp = angular.module('outdoorApp', ['ngRoute', 'usersControllers', 'usersServices']);


outdoorApp.factory('globalResponseInterceptor',['$q','$location','$rootScope',function($q,$location,$rootScope){
    return {
        'responseError': function(rejection) {
            // do something on error
            if (rejection.status === 401) {
                $location.path('#/users/auth');
            }
            return $q.reject(rejection);
        },
        'request': function(config) {
            config.headers.Authorization = 'Bearer ' + $rootScope.token;
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
            //.when('/posts/:postId', {
            //    templateUrl: 'partial/post-detail.html',
            //    controller: 'BlogDetailsCtrl'
            //})
            //.when('/posts/create/:postId', {
            //    templateUrl: 'partial/post-form.html',
            //    controller: 'BlogFormCtrl'
            //})
            //.when('/posts/create/new/', {
            //    templateUrl: 'partial/post-form.html',
            //    controller: 'BlogFormCtrl'
            //})
            //.when('/auth', {
            //    templateUrl: 'partial/user-form.html',
            //    controller: 'UserFormCtrl'
            //})
            .otherwise({
                'redirectTo': '/'
            });
    }
]);

outdoorApp.config([
    '$httpProvider',function($httpProvider) {
        $httpProvider.interceptors.push('globalResponseInterceptor');
    }
]);
