/**
 * CONFIG.HTTP.SERVICE
 */

var configService = angular.module('config.service',
    [
    	'backend.service'
	]);

configService.constant('URL_CONFIG_SERVICE', 
			{	
				SERVICE: '/config/service', 
				METHOD_UPDATE_CONFIG: '/updateConfig'
			});
			
																																																																																																				
configService.factory('configService', function(backendService, URL_CONFIG_SERVICE) {
	
	function updateConfig(successCallback, errorCallback, data, config)
	{	
		backendService.httpPut(URL_CONFIG_SERVICE.SERVICE + URL_CONFIG_SERVICE.METHOD_UPDATE_CONFIG, successCallback, errorCallback, data, config);
	}
	
	return{
		updateConfig: updateconfig
		}; 
});