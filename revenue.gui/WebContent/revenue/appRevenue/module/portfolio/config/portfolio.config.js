/**
 * PORTFOLIO.CONFIG
 */

var portfolioConfig = angular.module('portfolio.config',[
	'ngRoute', 
	'storage.service',
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