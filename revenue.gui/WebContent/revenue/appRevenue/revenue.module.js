/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.controller('ctrlRevenue', function($scope, $translate, breadcrumbService) {
	
	//EVENTLISTENER: breadcrumb from children
    $scope.$on('breadcrumb',function(event, data){
    	
    	breadcrumbService.set(data);

    	$scope.breadcrumb = breadcrumbService.get();
    	   	
      });

	$scope.changeLang = function(key) {
		$translate.use(key);
		
		$scope.breadcrumb = breadcrumbService.get();
	};

});
