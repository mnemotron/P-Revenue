/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.controller('ctrlRevenue', function($scope, $translate, breadcrumbService) {
	
	breadcrumbService.push({event:'+', text:'Home', link:'/'});
	$scope.breadcrumb = breadcrumbService.get();
	
	//EVENTLISTENER: breadcrumb from children
    $scope.$on('breadcrumb',function(event, data){

    	if (data.event == '+')
    	{
    		breadcrumbService.push(data);
    	}
    	else if(data.event == '-')
    	{
    		breadcrumbService.pop();
    	}
    	
    	$scope.breadcrumb = breadcrumbService.get();
    	
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
