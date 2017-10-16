/**
 * DEPOT.MODULE
 */

var depotModule = angular.module('depot.module', ['depot.config']);

depotModule.controller('ctrlViewCreateDepot', function($scope, $http, storageService, STORAGE_SERVICE_KEY, $location) {

	$scope.createDepot = function() {

		$scope.depot["portfolio"] = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		
		$http.post('http://localhost:8080/revenue.service/depot/service/createDepot', $scope.depot)	
		
		.then(function successCallback(response) {
			  $location.path( '/viewPortfolio' );
		}, 
		
		function errorCallback(response) {
			
		});
	};

});
