'use strict';

var usersControllers = angular.module('usersControllers', []);
var newsControllers = angular.module('newsControllers', []);
var activityControllers = angular.module('activityControllers', []);


usersControllers.controller('SignUpCtrl', ['$scope', '$rootScope','$location','Token', 'AppVariable', function ($scope, $rootScope, $location, Token, AppVariable) {
    var prefixSecu = 'client_id=webapp&client_secret=secret&grant_type=password&username=';
    $scope.login = function(user) {
        Token.auth({}, prefixSecu + user.username + '&password=' + user.password, function(response) {
            $rootScope.token = response.value;
            AppVariable.username = user.username;
            $rootScope.$broadcast(AppVariable.event_type.login_successful, user.username);

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


usersControllers.controller('UserManagementCtrl', function($scope, $rootScope, AppVariable) {
    $scope.username = AppVariable.username;

    $scope.anonymous = AppVariable.is_anonymous();
    
    var unbind = $rootScope.$on(AppVariable.event_type.login_successful, function(username){
        $scope.username = AppVariable.username;
        $scope.anonymous = AppVariable.is_anonymous();
    });

    $scope.$on('$destroy', unbind);
});


newsControllers.controller('NewsListCtrl', ['$scope', '$rootScope', 'News', 'AppVariable', function ($scope, $rootScope, News, AppVariable) {
    var unbind = $rootScope.$on(AppVariable.event_type.update_news, function(news){
        $scope.newsList = News.query();
    });

    $scope.$on('$destroy', unbind);

    $scope.newsList = News.query();
    $scope.orderNews = 'publishDate';
}
]);


// FIXME bad practice find a alternative with service for exemple
var lastSaved;

newsControllers.controller('AddNewsCtrl', ['$scope', '$rootScope','$location','News', 'AppVariable', function ($scope, $rootScope, $location, News, AppVariable) {
    $scope.display = !AppVariable.is_anonymous();
    $scope.publish = function(news) {
        //news.author = $rootScope.username; 
        news.author = AppVariable.username;
        News.create(news, function(response) {
            $rootScope.$broadcast(AppVariable.event_type.update_news, news);
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



activityControllers.controller('ActivityListCtrl', ['$scope', 'Activity', 'AppVariable', function ($scope, Activity, AppVariable) {
    //var unbind = $rootScope.$on(AppVariable.event_type.update_news, function(news){
    //    $scope.activities = Activity.query();
    //});

    //$scope.$on('$destroy', unbind);

    $scope.activities = Activity.query();
    $scope.orderActivity = 'viewsCounter';
}
]);


activityControllers.controller('ActivityDetailsCtrl', ['$scope', '$routeParams', 'Activity', 'AppVariable', function ($scope, $routeParams, Activity, AppVariable) {
    //var unbind = $rootScope.$on(AppVariable.event_type.update_news, function(news){
    //    $scope.activities = Activity.query();
    //});

    //$scope.$on('$destroy', unbind);

    $scope.activity = Activity.query({name: $routeParams.activityId}, function(activity) {
        $scope.activity = activity;
    });
    $scope.orderActivity = 'viewsCounter';
}
]);


activityControllers.controller('AddActivityCtrl', ['$scope', '$rootScope', '$routeParams', '$location','Activity', 'AppVariable', function ($scope, $rootScope, $routeParams, $location, Activity, AppVariable) {
    //$scope.display = !AppVariable.is_anonymous();
    $scope.prev_enable = false;

    if($routeParams.activityId) {
        Activity.query({name: $routeParams.activityId}, function(activity) {
            $scope.master = activity;
            $scope.reset();
        });
    }

    var editor = new wysihtml5.Editor("description", { // id of textarea element
        toolbar:      "wysihtml5-toolbar", // id of toolbar element
        parserRules:  wysihtml5ParserRules // defined in parser rules set 
    });


    $scope.publish = function(activity) {
        activity.creator = AppVariable.username;
        activity.description = editor.getValue();
        Activity.create(activity, function(data) {
            if(data.error) {
                $scope.error_returned = data.error;
            } else {
                $location.path('#/activity/' + activity.name);
            }
        });       
    };


    $scope.reset = function() {
         $scope.activity = angular.copy($scope.master);
    };

    //$('.wysihtml5-sandbox').show();
    editor.on("change", function() {
        $scope.prev_enable = true;
        $('#preview-content').html(editor.getValue());
    }).on("paste", function() {
        $('#preview-content').html(editor.getValue());
    })
    .on("newword:composer", function() {
        $('#preview-content').html(editor.getValue());
    })
    .on("undo:composer", function() {
        $('#preview-content').html(editor.getValue());
    })
    .on("redo:composer", function() {
        $('#preview-content').html(editor.getValue());
    });

}
]);


