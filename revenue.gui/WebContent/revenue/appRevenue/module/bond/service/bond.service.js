/**
 * BOND.HTTP.SERVICE
 */

var bondService = angular.module('bond.service',
    [
    	'backend.service'
	]);

bondService.constant('URL_BOND_SERVICE', 
			{	
				SERVICE: '/bond/service', 
				METHOD_GET_BOND_LIST: '/getBondList',
			});
			
																																																																																																				
bondService.factory('bondService', function(backendService, URL_BOND_SERVICE) {
	
	function getBondList(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_BOND_SERVICE.SERVICE + URL_BOND_SERVICE.METHOD_GET_BOND_LIST, successCallback, errorCallback, config);
	}
	
	return{
		getBondList: getBondList
	};
	
});