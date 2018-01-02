/**
 * ACCOUNT.HTTP.SERVICE
 */

var accountService = angular.module('account.service',
    [
    	'backend.service'
	]);

accountService.constant('URL_ACCOUNT_SERVICE', 
			{	
				SERVICE: '/account/service', 
				METHOD_GET_ACCOUNT_LIST: '/getAccountList',
				METHOD_CREATE_ACCOUNT: '/createAccount'
			});
			
																																																																																																				
accountService.factory('accountService', function(backendService, URL_ACCOUNT_SERVICE) {
	
	function getAccountList(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_ACCOUNT_SERVICE.SERVICE + URL_ACCOUNT_SERVICE.METHOD_GET_ACCOUNT_LIST, successCallback, errorCallback, config);
	}
	
	function createAccount(successCallback, errorCallback, data, config)
	{
		backendService.httpPost(URL_ACCOUNT_SERVICE.SERVICE + URL_ACCOUNT_SERVICE.METHOD_CREATE_ACCOUNT, successCallback, errorCallback, data, config);
	}
	
	return{
		getAccountList: getAccountList,
		createAccount: createAccount
	};
	
});