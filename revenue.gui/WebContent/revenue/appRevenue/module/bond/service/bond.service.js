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
				METHOD_CREATE_BOND: 'createBond',
				METHOD_DELETE_BOND: 'deleteBond',
				METHOD_GET_BOND_ITEM_BUY_LIST: 'getBondItemBuyList',
				METHOD_DELETE_BOND_ITEM_BUY: 'deleteBondItemBuy',
				METHOD_CREATE_BOND_ITEM_BUY: 'createBondItemBuy'
			});
			
																																																																																																				
bondService.factory('bondService', function(backendService, URL_BOND_SERVICE) {
	
	function getBondList(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_BOND_SERVICE.SERVICE + URL_BOND_SERVICE.METHOD_GET_BOND_LIST, successCallback, errorCallback, config);
	}
	
	function createBond(successCallback, errorCallback, data)
	{
		backendService.httpPost(URL_BOND_SERVICE.SERVICE + URL_BOND_SERVICE.METHOD_CREATE_BOND, successCallback, errorCallback, data);
	}
	
	function deleteBond(successCallback, errorCallback, config)
	{
		backendService.httpDelete(URL_BOND_SERVICE.SERVICE + URL_BOND_SERVICE.METHOD_DELETE_BOND, successCallback, errorCallback, config);
	}
	
	
	
	return{
		getBondList: getBondList,
		createBond: createBond,
		deleteBond: deleteBond
	};
	
});