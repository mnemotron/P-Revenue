/**
 * DEPOT.MODULE
 */

var depotModule = angular.module('depot.module', ['depot.config']);

depotModule.controller('ctrlViewDepot', function($scope, $http, $location, storageService, STORAGE_SERVICE_KEY) {
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.depot', link:'/viewDepot'});
	
	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);

	$http.get('http://localhost:8080/revenue.service/bond/service/getBondList', {params : {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id}}).then(function(response) {
		$scope.bonds = response.data
		
		for (var i = 0; i < $scope.bonds.length; i++) {
			$scope.bonds[i].interestDate = new Date($scope.bonds[i].interestDate);	
			$scope.bonds[i].dueDate = new Date($scope.bonds[i].dueDate);	
		}
	});
	
	$scope.selectBond = function(index) {
		storageService.set(STORAGE_SERVICE_KEY.BOND, $scope.bonds[index]);
	};
	
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

		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		
		$scope.depot["portfolioId"] = portfolio.id;
		
		$http.post('http://localhost:8080/revenue.service/depot/service/createDepot', $scope.depot)	
		
		.then(function successCallback(response) {
			  $location.path( '/viewPortfolio' );
		}, 
		
		function errorCallback(response) {
			
		});
	}

});
