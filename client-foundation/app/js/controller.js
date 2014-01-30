'use strict';

var usersControllers = angular.module('usersControllers', []);
var newsControllers = angular.module('newsControllers', []);


usersControllers.controller('SignUpCtrl', ['$scope', '$rootScope','$location','Token', function ($scope, $rootScope, $location, Token) {
    var prefixSecu = 'client_id=webapp&client_secret=secret&grant_type=password&username=';
    $scope.login = function(user) {
        Token.auth({}, prefixSecu + user.username + '&password=' + user.password, function(response) {
            $rootScope.token = response.value;
            $rootScope.username = user.username;
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



newsControllers.controller('NewsListCtrl', ['$scope', '$rootScope', 'News', function ($scope, $rootScope, News) {
    var unbind = $rootScope.$on('NEWS_CREATED', function(news){
        $scope.newsList = News.query();
    });

    $scope.$on('$destroy', unbind);

    $scope.newsList = News.query();
    $scope.orderProp = 'publishDate';
}
]);


// FIXME bad practice find a alternative with service for exemple
var lastSaved;

newsControllers.controller('AddNewsCtrl', ['$scope', '$rootScope','$location','News', function ($scope, $rootScope, $location, News) {


    $scope.publish = function(news) {
        news.author = $rootScope.username; 
        News.create(news, function(response) {
            $rootScope.$broadcast('NEWS_CREATED', news);
            lastSaved = undefined;
            $scope.reset();
        }, function(failure) {
            lastSaved = angular.copy(failure);
            $('#add_notification').show();
        });       
    };

    $scope.clean = function() {
        lastSaved = undefined;
    };

    $scope.reset = function() {
         $scope.user = angular.copy($scope.master);
    }

    if(lastSaved) {
        $('#add_notification').show();
    }

}
]);

