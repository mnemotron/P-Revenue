/**
 * STOCK.HTTP.SERVICE
 */

var stockService = angular.module('stock.service',
    [
    	'backend.service'
	]);

stockService.constant('URL_STOCK_SERVICE', 
			{	
				SERVICE: '/stock/service',
				METHOD_GET_HISTORICAL_QUOTES: '/getHistoricalQuotes',
			});
																																																																																																			
stockService.factory('stockService', function(backendService, URL_STOCK_SERVICE) {
	
	function getHistoricalQuotes(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_STOCK_SERVICE.SERVICE + URL_STOCK_SERVICE.METHOD_GET_HISTORICAL_QUOTES, successCallback, errorCallback, config);
	}
	
	return{
		getHistoricalQuotes : getHistoricalQuotes
	};
	
});