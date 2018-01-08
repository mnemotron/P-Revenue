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
				SERVICE: '/share/service',
				METHOD_GET_SHARE_LIST: '/getShareList',
				METHOD_CREATE_SHARE: '/createShare',
				METHOD_DELETE_SHARE: '/deleteShare'
			});
																																																																																																			
shareService.factory('shareService', function(backendService, stockService, URL_SHARE_SERVICE) {
	
	function getShareList(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_SHARE_SERVICE.SERVICE + URL_SHARE_SERVICE.METHOD_GET_SHARE_LIST, successCallback, errorCallback, config);
	}
	
	function createShare(successCallback, errorCallback, data, config)
	{
		backendService.httpPost(URL_SHARE_SERVICE.SERVICE + URL_SHARE_SERVICE.METHOD_CREATE_SHARE, successCallback, errorCallback, data, config);
	}
	
	function deleteShare(successCallback, errorCallback, config)
	{
		backendService.httpDelete(URL_SHARE_SERVICE.SERVICE + URL_SHARE_SERVICE.METHOD_DELETE_SHARE, successCallback, errorCallback, config);
	}
	
	function getHistoricalQuotes(successCallback, errorCallback, config)
	{	
		stockService.getHistoricalQuotes(successCallback, errorCallback, config);
	}
	
	return{
		getHistoricalQuotes: getHistoricalQuotes,
		getShareList: getShareList,
		createShare: createShare,
		deleteShare: deleteShare
	}
	
});