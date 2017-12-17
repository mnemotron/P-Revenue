/**
 * PORTFOLIO.HTTP.SERVICE
 */

var portfolioService = angular.module('portfolio.service',
    [
    	'server.service'
	]);

portfolioService.constant('URL_PORTFOLIO_SERVICE', 
			{	
				SERVICE: '/portfolio/service', 
				METHOD_GET_PORTFOLIO_LIST: '/getPortfolioList'
			});
			
																																																																																																				
portfolioService.factory('portfolioService', function($http, serverService, URL_PORTFOLIO_SERVICE) {
	
	function getPortfolioList(successCallback, errorCallback)
	{
		$http.get(serverService.getURLServerBrowser() + URL_PORTFOLIO_SERVICE.SERVICE + URL_PORTFOLIO_SERVICE.METHOD_GET_PORTFOLIO_LIST)
			.then(successCallback, errorCallback);
	}
	
	return{
		getPortfolioList: getPortfolioList
	};

    
});