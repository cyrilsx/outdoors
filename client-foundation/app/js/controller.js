'use strict';

var usersControllers = angular.module('usersControllers', []);


usersControllers.controller('SignUpCtrl', ['$scope', '$rootScope','$location','User', function ($scope, $rootScope, $location, User) {
    var prefixSecu = 'client_id=webapp&client_secret=secret&grant_type=password&username=';
    $scope.log = function(user) {
        User.auth({}, prefixSecu + user.username + '&password=' + user.password, function(response) {
            $rootScope.token = security.value;
            $location.path('/');
        }, 
        function(failure) {
            $scope.error = "Usename and password did'nt match";
        });
    };
}
]);


usersControllers.controller('RegisterCtrl', ['$scope', '$rootScope','$location','User', function ($scope, $rootScope, $location, User) {

    $scope.update = function(user) {
        User.create(user, function(response) {
            $('a.reveal-link').trigger('click');            
        });       
    };

    $scope.isValidPwd = function(user) {
        return angular.equals(user.passwordOne, user.passwordTwo);
    };

    $scope.reset = function() {
         $scope.user = angular.copy($scope.master);
    };

    $scope.reset();

    $scope.emailSent = function() {
        $location.path('/');
    };
}
]);


