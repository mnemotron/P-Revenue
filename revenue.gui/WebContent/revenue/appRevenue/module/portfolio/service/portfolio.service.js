/**
 * PORTFOLIO.HTTP.SERVICE
 */

var portfolioService = angular.module('portfolio.service',
    [
    	'backend.service'
	]);

portfolioService.constant('URL_PORTFOLIO_SERVICE', 
			{	
				SERVICE: '/portfolio/service', 
				METHOD_GET_PORTFOLIO_LIST: '/getPortfolioList',
				METHOD_CREATE_PORTFOLIO: '/createPortfolio',
				METHOD_DELETE_PORTFOLIO: '/deletePortfolio'
			});
			
																																																																																																				
portfolioService.factory('portfolioService', function(backendService, URL_PORTFOLIO_SERVICE) {
	
	function getPortfolioList(successCallback, errorCallback, config)
	{	
		backendService.httpGet(URL_PORTFOLIO_SERVICE.SERVICE + URL_PORTFOLIO_SERVICE.METHOD_GET_PORTFOLIO_LIST, successCallback, errorCallback, config);
	}
	
	function createPortfolio(successCallback, errorCallback, data)
	{
		backendService.httpPost(URL_PORTFOLIO_SERVICE.SERVICE + URL_PORTFOLIO_SERVICE.METHOD_CREATE_PORTFOLIO, successCallback, errorCallback, data);
	}
	
	function deletePortfolio(successCallback, errorCallback, config)
	{
		backendService.httpDelete(URL_PORTFOLIO_SERVICE.SERVICE + URL_PORTFOLIO_SERVICE.METHOD_DELETE_PORTFOLIO, successCallback, errorCallback, config);
	}
	
	return{
		getPortfolioList: getPortfolioList,
		createPortfolio: createPortfolio,
		deletePortfolio: deletePortfolio
	};

    
});