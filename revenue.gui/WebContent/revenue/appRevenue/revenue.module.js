/**
 * APPLICATION: REVENUE
 */

// MODULE
var appRevenue = angular.module("appRevenue", [ 'ngRoute' ]);

// CONFIG
appRevenue.config(function($routeProvider) { $routeProvider.when( "/",
							{
								templateUrl : "viewPortfolioLaunchpad.htm",
					            controller : "ctrlViewPortfolioLaunchpad"
							// }).when("/red", {
							// templateUrl : "red.htm"
							// }).when("/green", {
							// templateUrl : "green.htm"
							// }).when("/blue", {
							// templateUrl : "blue.htm"
							});
		});

// CONTROLLER
appRevenue.controller("ctrlViewPortfolioLaunchpad", function($scope, $http) {

	$http.get('http://localhost:8080/revenue.service/portfolio/getPortfolioList')
			.then(function(response) {
				$scope.portfolios = response.data
			});

});
