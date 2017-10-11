/**
 * PORTFOLIO.MODULE
 */

var portfolioModule = angular.module('portfolio.module', ['portfolio.config']);

portfolioModule.controller('ctrlViewPortfolioLaunchpad', function($scope, $http, serviceSelectPortfolio) {
	
	$scope.selectPortfolio = function(index) {
		
		console.log($scope.portfolios[index].name);
		
		serviceSelectPortfolio.setPortfolio($scope.portfolios[index])
	};

	$http.get('http://localhost:8080/revenue.service/portfolio/service/getPortfolioList')
			.then(function(response) {
				$scope.portfolios = response.data
			});

});