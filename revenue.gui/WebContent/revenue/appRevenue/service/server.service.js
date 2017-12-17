/**
 * SERVER.SERVICE
 */
var serverService = angular.module('server.service', []);

serverService.constant('URL_SERVER_DEFAULT', 
	{
		PROTOCOL: 'http',
		HOST: 'localhost',
		PORT: '8080',
	});

serverService.constant('URL_SERVICE',
    {
		SERVICE: 'revenue.service'
	});

serverService.factory('serverService', function($location, URL_SERVER_DEFAULT, URL_SERVICE) {
	
	function getURLServerDefault()
	{
		return URL_SERVER_DEFAULT.PROTOCOL + '://' + URL_SERVER_DEFAULT.HOST + ':' + URL_SERVER_DEFAULT.PORT + '/' + URL_SERVICE.SERVICE;
	}
	
	function getURLServerBrowser()
	{
		return $location.protocol() + '://' + $location.host() + ':' + $location.port() + '/' + URL_SERVICE.SERVICE;
	}
	
	return{
		getURLServerDefault: getURLServerDefault,
		getURLServerBrowser: getURLServerBrowser
	};
    
});