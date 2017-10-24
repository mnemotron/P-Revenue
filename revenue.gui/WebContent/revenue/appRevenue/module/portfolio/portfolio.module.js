/**
 * PORTFOLIO.MODULE
 */

var portfolioModule = angular.module('portfolio.module', ['portfolio.config']);

portfolioModule.controller('ctrlViewPortfolioLaunchpad', function($scope, $http, portfolioService, storageService, STORAGE_SERVICE_KEY) {

	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.home', link:'/'});
	
	$scope.selectPortfolio = function(index) {
		storageService.set(STORAGE_SERVICE_KEY.PORTFOLIO, $scope.portfolios[index]);
	};
	
	portfolioService.getPortfolioList($scope);

});

portfolioConfig.controller('ctrlViewCreatePortfolio', function($scope, $http, $location) {

	$scope.createPortfolio = function() {
		$http.post('http://localhost:8080/revenue.service/portfolio/service/createPortfolio', $scope.portfolio)
		
			.then(function successCallback(response) {
				  $location.path( '/' );
			}, 
			
			function errorCallback(response) {
				
			});
	}

});

portfolioConfig.controller('ctrlViewPortfolio', function($scope, $http, $location, storageService, STORAGE_SERVICE_KEY) {
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.portfolio', link:'/viewPortfolio'});
	
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);

	$http.get('http://localhost:8080/revenue.service/depot/service/getDepotList', {params : {id : $scope.selectedPortfolio.id}}).then(function(response) {
		$scope.depots = response.data
	});
	
	$scope.selectDepot = function(index) {
		storageService.set(STORAGE_SERVICE_KEY.DEPOT, $scope.depots[index]);
	};
	
	$scope.deletePortfolio = function(){
		$http.delete('http://localhost:8080/revenue.service/portfolio/service/deletePortfolio', {params: {id : $scope.selectedPortfolio.id }})
		.then(function successCallback(response) {
			  $location.path( '/' );
			  
		}, 
		
		function errorCallback(response) {
			
		});
	}

});