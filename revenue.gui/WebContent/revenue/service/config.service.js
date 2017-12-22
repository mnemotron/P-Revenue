/**
 * CONFIG.HTTP.SERVICE
 */

var configService = angular.module('config.service',
    [
    	'backend.service'
	]);

configService.constant('CONFIG_KEY',
    {
      	LANGUAGE: 'LANGUAGE',
      	CURRENCY: 'CURRENCY',
      	SPLASHSCREEN: 'SPLASH_SCREEN'
    });

configService.constant('URL_CONFIG_SERVICE', 
			{	
				SERVICE: '/config/service', 
				METHOD_UPDATE_CONFIG: '/updateConfig',
				METHOD_GET_CONFIG: '/getConfig',
				METHOD_GET_CONFIG_SINGLE: '/getConfigSingle'
			});
			
																																																																																																				
configService.factory('configService', function(backendService, URL_CONFIG_SERVICE) {
	
	function getConfig(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_CONFIG_SERVICE.SERVICE + URL_CONFIG_SERVICE.METHOD_GET_CONFIG, successCallback, errorCallback, config);
	}
	
	function getConfigSingle(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_CONFIG_SERVICE.SERVICE + URL_CONFIG_SERVICE.METHOD_GET_CONFIG_SINGLE, successCallback, errorCallback, config);
	}
	
	function updateConfig(successCallback, errorCallback, data, config)
	{	
		backendService.httpPut(URL_CONFIG_SERVICE.SERVICE + URL_CONFIG_SERVICE.METHOD_UPDATE_CONFIG, successCallback, errorCallback, data, config);
	}
	
	return{
		updateConfig: updateConfig,
		getConfig: getConfig,
		getConfigSingle: getConfigSingle
	} 
});