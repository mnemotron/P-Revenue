/**
 * BOND.CONFIG
 */

var bondConfig = angular.module('bond.config',[
	'ngRoute'
	]);

bondConfig.config(function($routeProvider){

	$routeProvider
	.when('/viewBond', {
		templateUrl : 'module/bond/view/bond.view.htm',
		controller : 'ctrlViewBond'
	});
	
});