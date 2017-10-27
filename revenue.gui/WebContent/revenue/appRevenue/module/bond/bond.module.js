/**
 * BOND.MODULE
 */

var bondModule = angular.module('bond.module', ['bond.config']);

bondModule.controller('ctrlViewBond', function($scope, $http) {

	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
	
	$http.get('http://localhost:8080/revenue.service/bond/service/getBondList', {params : {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id}}).then(function(response) {
		$scope.bonds = response.data
	});

});

bondModule.controller('ctrlViewAddBond', function($scope, $http, storageService, STORAGE_SERVICE_KEY,  $location) {

	$scope.createBond = function() {
		
		$scope.bond['portfolio'] = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		$scope.bond['depot'] = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		
		var interest = {'portfolio': $scope.bond['portfolio'], 'depot': $scope.bond['depot'], 'interest': $scope.bond['interest']};
		
//		var interestArray = new Array();
//		interestArray.push(interest);
		
//		$scope.bond['interest'] = interestArray;
	
		$http.post('http://localhost:8080/revenue.service/bond/service/createBondHeader', $scope.bond)	
	
		.then(function successCallback(response) {
			$location.path( '/viewBond' );
		}, 
	
		function errorCallback(response) {
		
		});	
	}

});

