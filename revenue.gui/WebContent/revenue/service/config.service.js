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
				METHOD_UPDATE_CONFIG: '/updateConfig',
				METHOD_GET_CONFIG: '/getConfig'
			});
			
																																																																																																				
configService.factory('configService', function(backendService, URL_CONFIG_SERVICE) {
	
	function getConfig(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_CONFIG_SERVICE.SERVICE + URL_CONFIG_SERVICE.METHOD_GET_CONFIG, successCallback, errorCallback, config);
	}
	
	function updateConfig(successCallback, errorCallback, data, config)
	{	
		backendService.httpPut(URL_CONFIG_SERVICE.SERVICE + URL_CONFIG_SERVICE.METHOD_UPDATE_CONFIG, successCallback, errorCallback, data, config);
	}
	
	return{
		updateConfig: updateConfig,
		getConfig: getConfig
	} 
});