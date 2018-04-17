var app = angular.module('loginApp',[]);

app.controller('loginController', ["$http", "$scope", "$location", "$window", 
	function($http, $scope, $location, $window){
	
	$scope.URL = '/configPage';
	$scope.authenticationCheck = function(){
		
		$scope.user = {
				username : $scope.username,
				password : $scope.password
		};
		
		$http.post('userLogin',$scope.user).
        then(function success(response) {
        	$window.location.href = $scope.URL;
        });
		
	}
}]);