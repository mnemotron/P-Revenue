/**
 * DEPOT.CONFIG
 */

var depotConfig = angular.module('depot.config',[
	'ngRoute',
	'storage.service',
	'bond.module'
	]);

depotConfig.config(function($routeProvider){
	
	$routeProvider
	.when('/viewDepot', {
		templateUrl : 'module/depot/view/depot.view.htm',
		controller : 'ctrlViewDepot'
	}).when('/viewAddBond', {
		templateUrl : 'module/bond/view/addBond.view.htm',
		controller : 'ctrlViewAddBond'
	});
	
});