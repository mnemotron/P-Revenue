/**
 * LOG.SERVICE
 */
var logService = angular.module('log.service', []);

logService.constant('LOGTYPE', {
	ERROR: 'E', 
	WARNING: 'W', 
	INFO: 'I', 
	SUCCESS: 'S'});

logService.factory('logService', function() {
	
	var log = [];
	
	function set(logModule, logType, logMsg)
	{
		log.push({date: '', module: logModule, type: logType, msg: logMsg});
	}
	
	function get()
	{
		return log;
	}
	
	return{
		set: set,
		get: get
	};

    
});