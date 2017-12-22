/**
 * REVENUETIMELINE.HTTP.SERVICE
 */

var revenueTimelineService = angular.module('revenueTimeline.service',
    [
    	'backend.service'
	]);

revenueTimelineService.constant('URL_REVENUE_TIMELINE_SERVICE', 
			{	
				SERVICE: '/revenue/service', 
				METHOD_GET_REVENUE_TIMELINE: '/getRevenueTimeline'
			});
			
																																																																																																				
revenueTimelineService.factory('revenueTimelineService', function(backendService, URL_REVENUE_TIMELINE_SERVICE) {
	
	function getRevenueTimeline(successCallback, errorCallback, data, config)
	{	
		backendService.httpPost(URL_REVENUE_TIMELINE_SERVICE.SERVICE + URL_REVENUE_TIMELINE_SERVICE.METHOD_GET_REVENUE_TIMELINE, successCallback, errorCallback, data, config);
	}
	
	return{
		getRevenueTimeline: getRevenueTimeline
	} 
});