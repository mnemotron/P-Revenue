/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.run(function ($rootScope, $translate) {
    $rootScope.$on('$translatePartialLoaderStructureChanged', function () {
      $translate.refresh();
    });
  });

appRevenueModule.controller('ctrlRevenue', function($scope, $translate, breadcrumbService, $translatePartialLoader, tmhDynamicLocale, REVENUE_LANGUAGE) {
	
	//EVENTLISTENER: breadcrumb from children
    $scope.$on('breadcrumb',function(event, data){
    	
    	breadcrumbService.set(data);

    	$scope.breadcrumb = breadcrumbService.get();
    	   	
      });

    //EVENTLISTENER: translate from children
    $scope.$on('translate', function(event, data){
    	 $translatePartialLoader.addPart(data.part);
    });
    
    //LANGUAGE AND LOCALIZATION
	$scope.changeLang = function(key) {
		
		$translate.use(key);
		
		$scope.breadcrumb = breadcrumbService.get();
		
		$scope.locale = key;
		
	};
	
	//LOCALIZATION 	
     $scope.$watch('locale', function(value, oldValue) {
         if(value !== oldValue) {
             tmhDynamicLocale.set(value);
         }
     });
     
 	$scope.locale = REVENUE_LANGUAGE.DEFAULT_LANGUAGE;
 	tmhDynamicLocale.set(REVENUE_LANGUAGE.DEFAULT_LANGUAGE);

});

appRevenueModule.controller('ctrlViewAbout', function($scope) {
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.about', link:'/viewAbout'});
	
});

appRevenueModule.controller('ctrlViewPreferences', function($scope) {
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.preferences', link:'/viewPreferences'});
});