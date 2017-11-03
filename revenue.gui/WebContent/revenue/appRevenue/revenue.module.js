/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.run(function ($rootScope, $translate) {
    $rootScope.$on('$translatePartialLoaderStructureChanged', function () {
      $translate.refresh();
    });
  });

appRevenueModule.controller('ctrlRevenue', function($scope, $translate, breadcrumbService, $translatePartialLoader) {
	
	//EVENTLISTENER: breadcrumb from children
    $scope.$on('breadcrumb',function(event, data){
    	
    	breadcrumbService.set(data);

    	$scope.breadcrumb = breadcrumbService.get();
    	   	
      });

    //EVENTLISTENER: translate from children
    $scope.$on('translate', function(event, data){
    	 $translatePartialLoader.addPart(data.part);
    });
    
	$scope.changeLang = function(key) {
		$translate.use(key);
		
		$scope.breadcrumb = breadcrumbService.get();
	};

});
