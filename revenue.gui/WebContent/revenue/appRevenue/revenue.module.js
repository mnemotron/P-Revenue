/**
 * APPLICATION: REVENUE
 */

// MODULE
var appRevenue = angular.module('appRevenue', ['ngRoute', 'ngSanitize', 'pascalprecht.translate']);

// CONFIG
appRevenue.config(function($routeProvider){
	
	$routeProvider.when('/', {
	templateUrl : 'viewPortfolioLaunchpad.htm',
	controller : 'ctrlViewPortfolioLaunchpad'
	}).when('/viewCreatePortfolio', {
		templateUrl : 'viewCreatePortfolio.htm',
		controller : 'ctrlViewCreatePortfolio'
	}).when('/viewPortfolio', {
		templateUrl : 'viewPortfolio.htm',
		controller : 'ctrlViewPortfolio'
	}).when('/viewCreateDepot', {
		templateUrl : 'viewCreateDepot.htm',
		controller : 'ctrlViewCreateDepot'
	});
});

appRevenue.config(function($translateProvider) {

	$translateProvider.useStaticFilesLoader({
	prefix : '../lang/lang-',
	suffix : '.json'
	});

	$translateProvider.preferredLanguage('de_DE');

	$translateProvider.useSanitizeValueStrategy('sanitize');

});

// SERVICE
appRevenue.factory("serviceSelectPortfolio", function(){
	
	var portfolio;
	
	function setPortfolio(locPortfolio)
	{
		portfolio = locPortfolio;
	}
	
	function getPortfolio()
	{
		return portfolio;
	}
	
	return{
		setPortfolio: setPortfolio,
		getPortfolio: getPortfolio
	};
	
});

// CONTROLLER
appRevenue.controller('ctrlTranslate', function($scope, $translate) {

	$scope.changeLang = function(key) {
		$translate.use(key)
		// .then(function (key) {
		// console.log("Sprache zu " + key + " gewechselt.");
		// }, function (key) {
		// console.log("Irgendwas lief schief.");
		// });
	};

});

appRevenue.controller('ctrlViewPortfolioLaunchpad', function($scope, $http, serviceSelectPortfolio) {
	
			$scope.selectPortfolio = function(index) {
				
				console.log($scope.portfolios[index].name);
				
				serviceSelectPortfolio.setPortfolio($scope.portfolios[index])
			};

			$http.get('http://localhost:8080/revenue.service/portfolio/service/getPortfolioList')
					.then(function(response) {
						$scope.portfolios = response.data
					});

		});

appRevenue.controller('ctrlViewCreatePortfolio', function($scope, $http) {

			$scope.createPortfolio = function() {
				$http.post('http://localhost:8080/revenue.service/portfolio/service/createPortfolio', $scope.portfolio);
			};

		});

appRevenue.controller('ctrlViewPortfolio', function($scope, $http, serviceSelectPortfolio) {

	$scope.selectedPortfolio = serviceSelectPortfolio.getPortfolio();
	
	$http.get('http://localhost:8080/revenue.service/depot/service/getDepotList', {params: {id: $scope.selectedPortfolio.id}})
	.then(function(response) {
		$scope.depots = response.data
	});
	
});

appRevenue.controller('ctrlViewCreateDepot', function($scope, $http) {

	$scope.createDepot = function() {
//		$http.post('http://localhost:8080/revenue.service/portfolio/service/createPortfolio', $scope.portfolio);
	};

});
