/**
 * PORTFOLIO.MODULE
 */

var portfolioModule = angular.module('portfolio.module', ['portfolio.config']);

portfolioModule.controller('ctrlViewPortfolioLaunchpad', function($scope, $http, portfolioService, storageService, logService, LOGTYPE, STORAGE_SERVICE_KEY, PORTFOLIO_LANGUAGE) {

	//EVENT: translate
	$scope.$emit('translate', {part:PORTFOLIO_LANGUAGE.PART});
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.home', link:'/'});

	$scope.selectPortfolio = function(index) {
		storageService.set(STORAGE_SERVICE_KEY.PORTFOLIO, $scope.portfolios[index]);
	}
	
	portfolioService.getPortfolioList(
		function successCallback(response){
			$scope.portfolios = response.data;
		}, 
		function errorCallback(response){
			logService.set('Revenue.Portfolio.PortfolioList', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type:'E', msgId:'viewPortfolio.portfolioList.notify.error'});
		});

});

portfolioModule.controller('ctrlViewCreatePortfolio', function($scope, $http, $location, portfolioService, logService, LOGTYPE, PORTFOLIO_LANGUAGE) {
 
//	//EVENT: translate
	$scope.$emit('translate', {part:PORTFOLIO_LANGUAGE.PART});
	
	$scope.createPortfolio = function() {
		
		portfolioService.createPortfolio(
				function successCallback(response){
					 $location.path( '/' );
				}, 
				function errorCallback(response){
					logService.set('Revenue.Portfolio.Create', LOGTYPE.ERROR, response.data);
					$scope.$emit('notify', {type:'E', msgId:'viewCreatePortfolio.form.create.notify.error'});
				}, 
				$scope.portfolio
		);
	}

});

portfolioModule.controller('ctrlViewPortfolio', function($scope, $http, $location, storageService, portfolioService, depotService, logService, LOGTYPE, STORAGE_SERVICE_KEY, PORTFOLIO_LANGUAGE) {
	
//	//EVENT: translate
	$scope.$emit('translate', {part:PORTFOLIO_LANGUAGE.PART});
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.portfolio', link:'/viewPortfolio'});
	
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);

	depotService.getDepotList(
			function successCallback(response){
				$scope.depots = response.data;
			}, 
			function errorCallback(response){
				logService.set('Revenue.Depot.DepotList', LOGTYPE.ERROR, response.data);
				$scope.$emit('notify', {type:'E', msgId:'viewPortfolio.depotList.notify.error'});	
			},
			{params : {id : $scope.selectedPortfolio.id}}
	);
	
	$scope.selectDepot = function(index) {
		storageService.set(STORAGE_SERVICE_KEY.DEPOT, $scope.depots[index]);
	};
	
	$scope.deletePortfolio = function(){
		
		portfolioService.deletePortfolio(
				function successCallback(response){
					 $location.path( '/' );
				}, 
				function errorCallback(response){
					logService.set('Revenue.Portfolio.Delete', LOGTYPE.ERROR, response.data);
					$scope.$emit('notify', {type:'E', msgId:'viewPortfolio.portfolio.delete.notify.error'});
				},
				{params: {id : $scope.selectedPortfolio.id }}
		);
	}

});