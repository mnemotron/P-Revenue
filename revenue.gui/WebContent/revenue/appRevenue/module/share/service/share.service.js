/**
 * SHARE.HTTP.SERVICE
 */

var shareService = angular.module('share.service',
    [
    	'backend.service'
	]);

shareService.constant('URL_SHARE_SERVICE', 
			{	
				SERVICE: '/share/service'
			});
			
																																																																																																				
shareService.factory('shareService', function(backendService, URL_SHARE_SERVICE) {
	
	return{

	};
	
});