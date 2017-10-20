/**
 * HTTP.SERVICE
 */

var portfolioService = angular.module('portfolio.service', []);

portfolioService.constant('URL_PORTFOLIO_SERVICE', 
			{	
				SERVER:  'http://localhost:8080',
				SERVICE: '/revenue.service/portfolio/service', 
				METHOD_GET_PORTFOLIO_LIST:  '/getPortfolioList'
			});
			
																																																																																																				
portfolioService.factory('portfolioService', function($http, URL_PORTFOLIO_SERVICE) {
	
	function getPortfolioList(scope)
	{
		$http.get(URL_PORTFOLIO_SERVICE.SERVER + URL_PORTFOLIO_SERVICE.SERVICE + URL_PORTFOLIO_SERVICE.METHOD_GET_PORTFOLIO_LIST).then(function(response) {
			scope.portfolios = response.data;
		});
	}
	
	return{
		getPortfolioList: getPortfolioList
	};

    
});