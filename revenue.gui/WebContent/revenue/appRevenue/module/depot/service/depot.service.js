/**
 * DEPOT.HTTP.SERVICE
 */

var depotService = angular.module('depot.service',
    [
    	'backend.service'
	]);

depotService.constant('URL_DEPOT_SERVICE', 
			{	
				SERVICE: '/depot/service', 
				METHOD_GET_DEPOT_LIST: '/getDepotList',
				METHOD_CREATE_DEPOT: '/createDepot',
				METHOD_DELETE_DEPOT: '/deleteDepot'
			});
			
																																																																																																				
depotService.factory('depotService', function(backendService, URL_DEPOT_SERVICE) {
	
	function getDepotList(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_DEPOT_SERVICE.SERVICE + URL_DEPOT_SERVICE.METHOD_GET_DEPOT_LIST, successCallback, errorCallback, config);
	}
	
	function createDepot(successCallback, errorCallback, data)
	{
		backendService.httpPost(URL_DEPOT_SERVICE.SERVICE + URL_DEPOT_SERVICE.METHOD_CREATE_DEPOT, successCallback, errorCallback, data);
	}
	
	function deleteDepot(successCallback, errorCallback, config)
	{
		backendService.httpDelete(URL_DEPOT_SERVICE.SERVICE + URL_DEPOT_SERVICE.METHOD_DELETE_DEPOT, successCallback, errorCallback, config);
	}
	
	return{
		getDepotList: getDepotList,
		createDepot: createDepot,
		deleteDepot: deleteDepot
	};
	
});