/**
 * BREADCRUMB.SERVICE
 */

var breadcrumbService = angular.module('breadcrumb.service', ['storage.service', 'pascalprecht.translate']);
																																																																																																				
breadcrumbService.factory('breadcrumbService', function(storageService, STORAGE_SERVICE_KEY, $translate) {
	
	var breadcrumb = new Array();
	var index = 0;
	
	function set(locBreadcrumb)
	{		
		var locFound = false;
		var locLength = breadcrumb.length;
		
		for (var i = 0; i < locLength; i++) {
			
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
		
//		storageService.set(STORAGE_SERVICE_KEY.BREADCRUMB, breadcrumb);	
	}
	
	function get()
	{
//		var breadcrumb = storageService.get(STORAGE_SERVICE_KEY.BREADCRUMB);
		
		for (var i = 0; i < breadcrumb.length; i++)
		{
				index = i;
				
				$translate(breadcrumb[i].id).then(function (translation) {
					breadcrumb[index].text = translation;
				});
		}

		return breadcrumb;
	}
	
	return{
		get: get,
		set: set
	};

    
});