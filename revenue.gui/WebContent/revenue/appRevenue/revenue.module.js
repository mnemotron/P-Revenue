/**
 * APPLICATION: REVENUE
 */

// MODULE
var appRevenue = angular.module('appRevenue', [ 'ngRoute' ]);

// CONFIG
appRevenue.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'viewPortfolioLaunchpad.htm',
		controller : 'ctrlViewPortfolioLaunchpad'
	}).when('/viewCreatePortfolio', {
		templateUrl : 'viewCreatePortfolio.htm'
	});
});

// CONTROLLER
appRevenue.controller('ctrlViewPortfolioLaunchpad', function($scope, $http) {

	$http.get(
			'http://localhost:8080/revenue.service/portfolio/getPortfolioList')
			.then(function(response) {
				$scope.portfolios = response.data
			});

});

appRevenue.controller('ctrlViewCreatePortfolio', function($scope, $http) {

	$scope.createPortfolio = function() {
		$http.post('http://localhost:8080/revenue.service/portfolio/createPortfolio', $scope.portfolio);
	};

});
