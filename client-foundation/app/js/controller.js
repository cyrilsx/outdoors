'use strict';

var usersControllers = angular.module('usersControllers', []);
var newsControllers = angular.module('newsControllers', []);


usersControllers.controller('SignUpCtrl', ['$scope', '$rootScope','$location','Token', function ($scope, $rootScope, $location, Token) {
    var prefixSecu = 'client_id=webapp&client_secret=secret&grant_type=password&username=';
    $scope.login = function(user) {
        Token.auth({}, prefixSecu + user.username + '&password=' + user.password, function(response) {
            $rootScope.token = response.value;
            $location.path('/');
        }, 
        function(failure) {
            if(failure['error-description'])
                $scope.error = failure['error-description'];
            else
                $scope.error = 'Authentication impossible!!!';
        });
    };
    
    $scope.reset = function() {
         $scope.user = angular.copy($scope.master);
    };

    $scope.reset();
}
]);


usersControllers.controller('RegisterCtrl', ['$scope', '$rootScope','$location','User', function ($scope, $rootScope, $location, User) {

    $scope.update = function(user) {
        user.password = user.passwordOne;
        delete user.passwordOne;
        delete user.passwordTwo;
        User.create(user, function(response) {
            $('#confirmation').show();
        });       
    };

    $scope.isValidPwd = function(user) {
        return user && user.passwordOne && user.passwordTwo && angular.equals(user.passwordOne, user.passwordTwo);
    };

    $scope.reset = function() {
        $('#confirmation').hide();
         $scope.user = angular.copy($scope.master);
    };

    $scope.reset();

    $scope.emailSent = function() {
        $location.path('/');
    };
}
]);



newsControllers.controller('NewsListCtrl', ['$scope', 'News', function ($scope, News) {
    $scope.newsList = News.query();
    $scope.orderProp = 'publishDate';
}
]);



newsControllers.controller('AddNewsCtrl', ['$scope', '$rootScope','$location','News', function ($scope, $rootScope, $location, News) {

    $scope.publish = function(news) {
        News.create(news, function(response) {

        });       
    };

    $scope.reset = function() {
         $scope.user = angular.copy($scope.master);
    };

    $scope.reset();

}
]);

