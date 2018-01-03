/**
 * STORAGE.SERVICE
 */

var storageService = angular.module('storage.service', []);

storageService.constant('STORAGE_SERVICE_KEY', {
	BREADCRUMB: 'breadcrumb', 
	PORTFOLIO: 'portfolio', 
	ACCOUNT: 'account',
	DEPOT: 'depot', 
	BOND: 'bond',
	SHARE: 'share'
	});
																																																																																																				
storageService.factory('storageService', function($localStorage) {
	
	function set(key, value)
	{
		if ($localStorage.data === undefined)
		{
			$localStorage.data = {[key] : value};
		}
		else
		{
			$localStorage.data[key] = value;
		}
	}
	
	function get(key)
	{
		var locData = $localStorage.data;
		
		if (locData === undefined)
		{
			return undefined;
		}
		else
		{
		return locData[key];	
		}
	}
	
	function del(key)
	{
		delete $localStorage.data[key];
	}
	
	return{
		set: set,
		get: get,
		del: del
	};

    
});