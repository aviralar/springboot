var app = angular.module('configureApp',[]);

app.controller('configureController', ["$http", "$scope", "$location", "$window", 
	function($http, $scope, $location, $window){
	
	$scope.URL = '/dashboard';
	$scope.Logout = function() {
		$window.location.href="/index";
	}
	
	$scope.submitConfigData = function() {
		$scope.configData = {
				rotorMass : $scope.rotorMass,
				cylinderDiameter : $scope.cylinderDiameter,
				cylinderNo : $scope.cylinderNo,
				bearingSafetyFactor : $scope.bearingSafetyFactor,
				bearngCenterDistance : $scope.bearngCenterDistance,
				bearingA : $scope.bearingA,
				bearingB : $scope.bearingB,
				bearingDynamicLoad : $scope.bearingDynamicLoad,
				lifeExponent : $scope.lifeExponent,
				bearingFatigueLoad : $scope.bearingFatigueLoad,
				factorX : $scope.factorX,
				factorY : $scope.factorY,
				bearingID : $scope.bearingID,
				bearingOD : $scope.bearingOD
		};
		
		console.log($scope.bearingDynamicLoad);
		$http.post('submitConfigData',$scope.configData).
        then(function success(response) {
        	$window.location.href = $scope.URL;
        });
	}
}]);