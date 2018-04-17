var app = angular.module('loginApp',[]);

app.controller('loginController', function($scope,$window){

	$scope.authenticationCheck = function(){
		if($scope.username == "admin")
			if($scope.password == "admin")
				$window.location.href = "configPage.html";
			else
				{
					window.alert("password is incorrect");
					$scope.password="";
				}
		else
			{
				window.alert("username is incorrect");		
				$scope.password="";
				$scope.username="";
			}
	}
});