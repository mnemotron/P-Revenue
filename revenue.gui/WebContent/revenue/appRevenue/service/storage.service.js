/**
 * STORAGE.SERVICE
 */

var storageService = angular.module('storage.service', []);

storageService.constant('STORAGE_SERVICE_KEY', {BREADCRUMB: 'breadcrumb', PORTFOLIO: 'portfolio', DEPOT: 'depot'});
																																																																																																				
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
		
		return locData[key];	
	}
	
	return{
		set: set,
		get: get
	};

    
});