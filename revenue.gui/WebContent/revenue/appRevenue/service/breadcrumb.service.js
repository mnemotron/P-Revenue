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
	
	function set(locBreadcrumb)
	{		
		var locFound = false;
		
		for (var i = 0; i < breadcrumb.length; i++) {
			
			if (locFound == true)
			{
				breadcrumb.pop();
			}
			else if (breadcrumb[i].link == locBreadcrumb.link)
			{
				locFound = true;
			}
			
		}
		
		if(locFound == false)
		{
			breadcrumb.push(locBreadcrumb);
		}
	}
	
	function get()
	{
		return breadcrumb;
	}
	
	return{
		get: get,
		set: set
	};

    
});