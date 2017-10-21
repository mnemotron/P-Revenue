/**
 * DEPOT.MODULE
 */

var depotModule = angular.module('depot.module', ['depot.config']);

depotModule.controller('ctrlViewDepot', function($scope, $http, $location, storageService, STORAGE_SERVICE_KEY) {
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {text:'Depot', link:'/viewDepot'});
	
	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);

	$scope.deleteDepot = function(){
		$http.delete('http://localhost:8080/revenue.service/depot/service/deleteDepot', {params: {id : $scope.selectedDepot.id}})
		.then(function successCallback(response) {
			  $location.path( '/viewPortfolio' );
			  
		}, 
		
		function errorCallback(response) {
			
		});
	}

});

depotModule.controller('ctrlViewCreateDepot', function($scope, $http, storageService, STORAGE_SERVICE_KEY, $location) {

	$scope.createDepot = function() {

		$scope.depot["portfolio"] = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		
		$http.post('http://localhost:8080/revenue.service/depot/service/createDepot', $scope.depot)	
		
		.then(function successCallback(response) {
			  $location.path( '/viewPortfolio' );
		}, 
		
		function errorCallback(response) {
			
		});
	}

});
