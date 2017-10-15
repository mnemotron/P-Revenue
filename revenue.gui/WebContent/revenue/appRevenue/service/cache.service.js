/**
 * CACHE.SERVICE
 */

var cacheService = angular.module('cache.service', []);

cacheService.factory("serviceCache", function(){
	
	var portfolio;
	var depot;
	
	function setPortfolio(locPortfolio)
	{
		portfolio = locPortfolio;
	}
	
	function getPortfolio()
	{
		return portfolio;
	}
	
	function setDepot(locDepot)
	{
		depot = locDepot;
	}
	
	function getDepot()
	{
		return depot;
	}
	
	return{
		setPortfolio: setPortfolio,
		getPortfolio: getPortfolio,
		setDepot: setDepot,
		getDepot: getDepot
	};
	
});