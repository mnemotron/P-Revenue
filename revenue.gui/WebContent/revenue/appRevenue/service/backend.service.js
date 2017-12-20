/**
 * BACKEND.SERVICE
 */
var backendService = angular.module('backend.service', []);

backendService.constant('URL_BACKEND_DEFAULT', 
	{
		PROTOCOL: 'http',
		HOST: 'localhost',
		PORT: '8080',
	});

backendService.constant('URL_BACKEND_SERVICE_DEFAULT',
    {
		SERVICE: 'revenue.service'
	});

backendService.factory('backendService', function($http, $location, URL_BACKEND_DEFAULT, URL_BACKEND_SERVICE_DEFAULT) {
	
	function getURLBackendDefault()
	{
		return URL_BACKEND_DEFAULT.PROTOCOL + '://' + URL_BACKEND_DEFAULT.HOST + ':' + URL_BACKEND_DEFAULT.PORT + '/' + URL_BACKEND_SERVICE_DEFAULT.SERVICE;
	}
	
	function getURLBackendBrowser()
	{
		return $location.protocol() + '://' + $location.host() + ':' + $location.port() + '/' + URL_BACKEND_SERVICE_DEFAULT.SERVICE;
	}
	
	function getURLBackend(service)
	{
		return getURLBackendDefault() + service;
	}
	
	function httpGet(service, successCallback, errorCallback, config)
	{
		$http.get(getURLBackend(service), config).then(successCallback, errorCallback);
	}
	
	function httpPost(service, successCallback, errorCallback, data, config)
	{
		$http.post(getURLBackend(service), data, config).then(successCallback, errorCallback);
	}
	
	function httpDelete(service, successCallback, errorCallback, config)
	{
		$http.delete(getURLBackend(service), config).then(successCallback, errorCallback);
	}
	
	return{
		getURLBackendDefault: getURLBackendDefault,
		getURLBackendBrowser: getURLBackendBrowser,
		getURLBackend: getURLBackend,
		httpGet: httpGet,
		httpPost: httpPost,
		httpDelete: httpDelete
	};
    
});