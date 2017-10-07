/**
 * APPLICATION: REVENUE
 */

// MODULE
var appRevenue = angular.module('appRevenue', [ 'ngRoute', 'ngSanitize', 'pascalprecht.translate' ]);

// CONFIG
appRevenue.config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'viewPortfolioLaunchpad.htm',
		controller : 'ctrlViewPortfolioLaunchpad'
	}).when('/viewCreatePortfolio', {
		templateUrl : 'viewCreatePortfolio.htm'
	});
});

appRevenue.config(function($translateProvider) {
	
	$translateProvider.useStaticFilesLoader({
	    prefix: '../lang/lang-',
	    suffix: '.json'
	  });

	  $translateProvider.preferredLanguage('de_DE');
	  
	  $translateProvider.useSanitizeValueStrategy('sanitize');
	  
});

// CONTROLLER
appRevenue.controller('ctrlTranslate', function($scope, $translate) {

	$scope.changeLang = function (key) {
  $translate.use(key)
//  .then(function (key) {
//    console.log("Sprache zu " + key + " gewechselt.");
//  }, function (key) {
//    console.log("Irgendwas lief schief.");
//  });
};

});

appRevenue.controller('ctrlViewPortfolioLaunchpad', function($scope, $http) {

	$http.get(
			'http://localhost:8080/revenue.service/portfolio/getPortfolioList')
			.then(function(response) {
				$scope.portfolios = response.data
			});

});

appRevenue.controller('ctrlViewCreatePortfolio',function($scope, $http) {

					$scope.createPortfolio = function() {
						$http.post('http://localhost:8080/revenue.service/portfolio/createPortfolio', $scope.portfolio);
					};

});
