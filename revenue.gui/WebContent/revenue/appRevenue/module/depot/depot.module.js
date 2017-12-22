/**
 * DEPOT.MODULE
 */

var depotModule = angular.module('depot.module', ['depot.config']);

depotModule.controller('ctrlViewDepot', function($scope, $location, storageService, logService, depotService, bondService, LOGTYPE, STORAGE_SERVICE_KEY, DEPOT_LANGUAGE) {
	
	//EVENT: translate
	$scope.$emit('translate', {part:DEPOT_LANGUAGE.PART});
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.depot', link:'/viewDepot'});
	
	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);

	bondService.getBondList(
			function successCallback(response){
				$scope.bonds = response.data;
				
				for (var i = 0; i < $scope.bonds.length; i++) {
				$scope.bonds[i].interestDate = new Date($scope.bonds[i].interestDate);	
				$scope.bonds[i].dueDate = new Date($scope.bonds[i].dueDate);	
				}
				
			}, 
			function errorCallback(response){
				logService.set('Revenue.Depot.DepotList', LOGTYPE.ERROR, response.data);
				$scope.$emit('notify', {type:'E', msgId:'viewDepot.bondList.notify.error'});	
			},
			{params : {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id}}
	);
	
	$scope.selectBond = function(index) {
		storageService.set(STORAGE_SERVICE_KEY.BOND, $scope.bonds[index]);
	};
	
	$scope.deleteDepot = function(){
		
		depotService.deleteDepot(
				function successCallback(response){
					 $location.path( '/viewPortfolio' );
				}, 
				function errorCallback(response){
					logService.set('Revenue.Depot.Delete', LOGTYPE.ERROR, response.data);
					$scope.$emit('notify', {type:'E', msgId:'viewDepot.depot.delete.notify.error'});
				},
				{params: {id : $scope.selectedDepot.id}}
		);

	}

});

depotModule.controller('ctrlViewCreateDepot', function($scope, storageService, logService, depotService, LOGTYPE, STORAGE_SERVICE_KEY, $location, DEPOT_LANGUAGE) {

	//EVENT: translate
	$scope.$emit('translate', {part:DEPOT_LANGUAGE.PART});
	
	$scope.createDepot = function() {

		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		
		$scope.depot['portfolioId'] = portfolio.id;
		
		depotService.createDepot(
				function successCallback(response){
					$location.path( '/viewPortfolio' );
				}, 
				function errorCallback(response){
					logService.set('Revenue.Depot.Create', LOGTYPE.ERROR, response.data);
					$scope.$emit('notify', {type:'E', msgId:'viewCreateDepot.form.create.notify.error'});
				}, 
				$scope.depot
		);
	}

});
