/**
 * SELECTPORTFOLIO.SERVICE
 */

var selectPortfolioService = angular.module('selectPortfolio.service', []);

selectPortfolioService.factory("serviceSelectPortfolio", function(){
	
	var portfolio;
	
	function setPortfolio(locPortfolio)
	{
		portfolio = locPortfolio;
	}
	
	function getPortfolio()
	{
		return portfolio;
	}
	
	return{
		setPortfolio: setPortfolio,
		getPortfolio: getPortfolio
	};
	
});