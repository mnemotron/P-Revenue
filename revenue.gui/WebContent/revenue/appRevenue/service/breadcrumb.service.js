/**
 * BREADCRUMB.SERVICE
 */

var breadcrumbService = angular.module('breadcrumb.service', ['storage.service', 'pascalprecht.translate']);
																																																																																																				
breadcrumbService.factory('breadcrumbService', function(storageService, STORAGE_SERVICE_KEY, $translate) {
	
	var index = 0;
	
	function set(locBreadcrumb)
	{		
		var breadcrumb = storageService.get(STORAGE_SERVICE_KEY.BREADCRUMB);
		
		if (breadcrumb == undefined)
		{
			breadcrumb = new Array();
		}
		
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
		
		storageService.set(STORAGE_SERVICE_KEY.BREADCRUMB, breadcrumb);	
	}
	
	function get()
	{
		var breadcrumb = storageService.get(STORAGE_SERVICE_KEY.BREADCRUMB);
		
		for (var i = 0; i < breadcrumb.length; i++)
		{
				index = i;
				
				$translate(breadcrumb[i].id).then(function (translation) {
					
					breadcrumb[index].text = translation;
				});	
				
//				var translater = translateAsy(i);
//				
//				translater(breadcrumb);
				
		}

		return breadcrumb;
	}
	
//	function translateAsy(tindex)
//	{
//		function translater(breadcrumb)
//		{
//			$translate(breadcrumb[tindex].id).then(function (translation) {
//				
//				breadcrumb[tindex].text = translation;
//			});	
//		}
//		
//		return translater
//	}

	return{
		get: get,
		set: set
	};

    
});