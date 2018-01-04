/**
 * SHARE.HTTP.SERVICE
 */

var shareService = angular.module('share.service',
    [
    	'backend.service',
    	'stock.service'
	]);

shareService.constant('URL_SHARE_SERVICE', 
			{	
				SERVICE: '/share/service'
			});
																																																																																																			
shareService.factory('shareService', function(stockService, URL_SHARE_SERVICE) {
	
	function getHistoricalQuotes(successCallback, errorCallback, config)
	{	
		stockService.getHistoricalQuotes(successCallback, errorCallback, config);
	}
	
	return{
		getHistoricalQuotes : getHistoricalQuotes
	}
	
});