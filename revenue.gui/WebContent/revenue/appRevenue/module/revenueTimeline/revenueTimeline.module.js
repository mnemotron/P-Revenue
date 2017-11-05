/**
 * REVENUE.TIMELINE.MODULE
 */

var revenueTimelineModule = angular.module('revenue.timeline.module', ['revenue.timeline.config']);

revenueTimelineModule.controller('ctrlViewRevenueTimeline', function($scope, $http, storageService, STORAGE_SERVICE_KEY) {
	
	//EVENT: translate
//	$scope.$emit('translate', {part:BOND_LANGUAGE.PART});
	
	//EVENT: breadcrumb
//	$scope.$emit('breadcrumb', {id:'breadcrumb.bond', link:'/viewBond'});

	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
	$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);
	
	var bondIdList = [$scope.selectedBond.id];
	var reqRevenueTimeline = {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id, bondIdList: bondIdList};
	
	$http.post('http://localhost:8080/revenue.service/revenue/service/getRevenueTimeline', reqRevenueTimeline).then(function(response) {

	});

});

