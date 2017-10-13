/**
 * PORTFOLIO.MODULE
 */

var portfolioModule = angular.module('portfolio.module', ['portfolio.config']);

portfolioModule.controller('ctrlViewPortfolioLaunchpad', function($scope, $http, serviceSelectPortfolio) {

	$scope.selectPortfolio = function(index) {

		console.log($scope.portfolios[index].name);

		serviceSelectPortfolio.setPortfolio($scope.portfolios[index])
	};

	$http.get('http://localhost:8080/revenue.service/portfolio/service/getPortfolioList').then(function(response) {
		$scope.portfolios = response.data
	});

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

portfolioConfig.controller('ctrlViewPortfolio', function($scope, $http, serviceSelectPortfolio, $location) {

	$scope.selectedPortfolio = serviceSelectPortfolio.getPortfolio();

	$http.get('http://localhost:8080/revenue.service/depot/service/getDepotList', {params : {id : $scope.selectedPortfolio.id}}).then(function(response) {
		$scope.depots = response.data
	});
	
	$scope.deletePortfolio = function(){
		$http.delete('http://localhost:8080/revenue.service/portfolio/service/deletePortfolio', {params: {id : $scope.selectedPortfolio.id }})
		.then(function successCallback(response) {
			  $location.path( '/' );
		}, 
		
		function errorCallback(response) {
			
		});
	}

});