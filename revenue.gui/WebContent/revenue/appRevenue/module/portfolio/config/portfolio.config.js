/**
 * PORTFOLIO.CONFIG
 */

var portfolioConfig = angular.module('portfolio.config',[
	'ngRoute', 
	'storage.service',
	'log.service',
	'depot.module',
	'depot.service',
	'portfolio.service'
	]);

portfolioConfig.constant('PORTFOLIO_LANGUAGE', {PART: 'module/portfolio/lang'});

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