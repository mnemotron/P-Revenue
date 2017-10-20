/**
 * BREADCRUMB.SERVICE
 */

var breadcrumbService = angular.module('breadcrumb.service', ['storage.service']);
																																																																																																				
breadcrumbService.factory('breadcrumbService', function(storageService, STORAGE_SERVICE_KEY) {
	
//	function push(breadcrumb)
//	{
//		
//		storageService.get(STORAGE_SERVICE_KEY.BREADCRUMB);
//		
//		storageService.set(STORAGE_SERVICE_KEY.BREADCRUMB, $scope.portfolios[index]);	
//	}
//	
//	function pop()
//	{
//		
//	}
	
	var breadcrumb = new Array();
	
	function push(locBreadcrumb)
	{		
		breadcrumb.push(locBreadcrumb);
	}
	
	function pop()
	{		
		breadcrumb.pop();
	}
	
	function get()
	{
		return breadcrumb;
	}
	
	return{
		get: get,
		push: push,
		pop: pop
	};

    
});