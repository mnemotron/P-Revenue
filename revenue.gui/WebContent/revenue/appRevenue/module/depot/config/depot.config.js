/**
 * DEPOT.CONFIG
 */

var depotConfig = angular.module('depot.config',[
	'ngRoute',
	'storage.service',
	]);

depotConfig.config(function($routeProvider){
	
	$routeProvider
	.when('/viewDepot', {
		templateUrl : 'module/depot/view/depot.view.htm',
		controller : 'ctrlViewDepot'
	});
	
});