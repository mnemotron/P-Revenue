/**
 * BOND.MODULE
 */

var bondModule = angular.module('bond.module', ['bond.config']);

bondModule.controller('ctrlViewBond', function($scope, $http, storageService, STORAGE_SERVICE_KEY, $location, BOND_LANGUAGE) {
	
	//EVENT: translate
	$scope.$emit('translate', {part:BOND_LANGUAGE.PART});
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.bond', link:'/viewBond'});
	
	$scope.getBond = function(){
		
		$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);
		
		$http.get('http://localhost:8080/revenue.service/bond/service/getBondItemBuyList', {params : {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id, bondId: $scope.selectedBond.id}}).then(function(response) {
			$scope.bondItemBuyList = response.data

			for (var i = 0; i < $scope.bondItemBuyList.length; i++) {
			$scope.bondItemBuyList[i].buyDate = new Date($scope.bondItemBuyList[i].buyDate);	
			}
		});
		
	}
	
	$scope.deleteBond = function(){
		$http.delete('http://localhost:8080/revenue.service/bond/service/deleteBond', {params: {bondId : $scope.selectedBond.id}})
		.then(function successCallback(response) {
			  $location.path( '/viewDepot' );
			  
		}, 
		
		function errorCallback(response) {
			
		});
	}
	
	$scope.deleteBondItemBuy = function(index){
		
		var bondItemBuyId = $scope.bondItemBuyList[index].id;
		
		$http.delete('http://localhost:8080/revenue.service/bond/service/deleteBondItemBuy', {params: {bondItemBuyId : bondItemBuyId}})
		.then(function successCallback(response) {
			$scope.getBond();
		}, 
		
		function errorCallback(response) {
			
		});
	}
	
	$scope.getBond();

});

bondModule.controller('ctrlViewAddBond', function($locale, $scope, $http, storageService, STORAGE_SERVICE_KEY,  $location, BOND_LANGUAGE) {

	//INIT
    $scope.dateFormat = $locale.DATETIME_FORMATS.shortDate
	
	//EVEN: translate
	$scope.$emit('translate', {part:BOND_LANGUAGE.PART});
	
    //EVENTLISTENER: localization change
	$scope.$on('$localeChangeSuccess', function(event, data){
		$scope.dateFormat = $locale.DATETIME_FORMATS.shortDate;
	
	});
	
	//METHOD
	$scope.createBond = function() {
		
		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		var depot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		
		$scope.bond['portfolioId'] = portfolio.id;
		$scope.bond['depotId'] = depot.id;
		
		$http.post('http://localhost:8080/revenue.service/bond/service/createBond', $scope.bond)	
	
		.then(function successCallback(response) {
			$location.path( '/viewDepot' );
		}, 
	
		function errorCallback(response) {
		
		});	
	}

});

bondModule.controller('ctrlViewAddBondItemBuy', function($locale, $scope, $http, storageService, STORAGE_SERVICE_KEY,  $location, BOND_LANGUAGE) {

	//INIT
    $scope.dateFormat = $locale.DATETIME_FORMATS.shortDate
	
	//EVEN: translate
	$scope.$emit('translate', {part:BOND_LANGUAGE.PART});
	
    //EVENTLISTENER: localization change
	$scope.$on('$localeChangeSuccess', function(event, data){
		$scope.dateFormat = $locale.DATETIME_FORMATS.shortDate;
	
	});
	
	//METHOD
	$scope.createBondItemBuy = function () {
		
		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		var depot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		var bond = storageService.get(STORAGE_SERVICE_KEY.BOND);
			
		$scope.bondItemBuy['portfolioId'] = portfolio.id;
		$scope.bondItemBuy['depotId'] = depot.id;
		$scope.bondItemBuy['bondId'] = bond.id;																																																																																																
		
		$http.post('http://localhost:8080/revenue.service/bond/service/createBondItemBuy', $scope.bondItemBuy)	
		
		.then(function successCallback(response) {
			$location.path( '/viewBond' );
		}, 
	
		function errorCallback(response) {
		
		});	
		
	}

});

