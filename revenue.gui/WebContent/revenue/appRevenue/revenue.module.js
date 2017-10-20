/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.controller('ctrlTranslate', function($scope, $translate, breadcrumbService) {
	
    $scope.$on('breadcrumb',function(event){
    	$scope.breadcrumb = breadcrumbService.get();
    	console.log($scope.breadcrumb);
      });

	$scope.changeLang = function(key) {
		$translate.use(key)
		// .then(function (key) {
		// console.log("Sprache zu " + key + " gewechselt.");
		// }, function (key) {
		// console.log("Irgendwas lief schief.");
		// });
	};

});
