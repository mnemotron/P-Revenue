/**
 * BOND.MODULE
 */

var bondModule = angular.module('bond.module', ['bond.config']);

bondModule.controller('ctrlViewBond', function($scope, $http, storageService, STORAGE_SERVICE_KEY) {

	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
	$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);
	
	$http.get('http://localhost:8080/revenue.service/bond/service/getBondItemBuyList', {params : {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id, bondId: $scope.selectedBond.id}}).then(function(response) {
		$scope.bondItemBuyList = response.data
	});

});

bondModule.controller('ctrlViewAddBond', function($scope, $http, storageService, STORAGE_SERVICE_KEY,  $location) {

	$scope.createBond = function() {
		
		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		var depot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		
		$scope.bond['portfolioId'] = portfolio.id;
		$scope.bond['depotId'] = depot.id;
		
		$http.post('http://localhost:8080/revenue.service/bond/service/createBond', $scope.bond)	
	
		.then(function successCallback(response) {
			$location.path( '/viewBond' );
		}, 
	
		function errorCallback(response) {
		
		});	
	}

});

