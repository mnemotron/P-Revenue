/**
 * PORTFOLIO.CONFIG
 */

var portfolioConfig = angular.module('portfolio.config',[
	'ngRoute', 
	'selectPortfolio.service',
	'depot.module'
	]);

portfolioConfig.config(function($routeProvider){
	
	$routeProvider
		.when('/', {
			templateUrl : 'module/portfolio/view/portfolioLaunchpad.view.htm',
			controller : 'ctrlViewPortfolioLaunchpad'
		})
		.when('/viewCreatePortfolio', {
			templateUrl : 'module/portfolio/view/createPortfolio.view.htm',
			controller : 'ctrlViewCreatePortfolio'
		})
		.when('/viewPortfolio', {
			templateUrl : 'module/portfolio/view/portfolio.view.htm',
			controller : 'ctrlViewPortfolio'
		})
		.when('/viewCreateDepot', {
			templateUrl : 'module/depot/view/createDepot.view.htm',
			controller : 'ctrlViewCreateDepot'
		});
});

portfolioConfig.controller('ctrlViewCreatePortfolio', function($scope, $http) {

	$scope.createPortfolio = function() {
		$http.post('http://localhost:8080/revenue.service/portfolio/service/createPortfolio', $scope.portfolio);
	};

});

portfolioConfig.controller('ctrlViewPortfolio', function($scope, $http, serviceSelectPortfolio) {

$scope.selectedPortfolio = serviceSelectPortfolio.getPortfolio();

$http.get('http://localhost:8080/revenue.service/depot/service/getDepotList', {params: {id: $scope.selectedPortfolio.id}})
.then(function(response) {
$scope.depots = response.data
});

});