/**
 * BOND.CONFIG
 */

var bondConfig = angular.module('bond.config',[
	'ngRoute',
	'storage.service',
	'dateParser'
	]);

bondConfig.constant('BOND_LANGUAGE', {PART: 'module/bond/lang'});

bondConfig.config(function($routeProvider){

	$routeProvider
	.when('/viewBond', {
		templateUrl : 'module/bond/view/bond.view.htm',
		controller : 'ctrlViewBond'
	}).when('/viewAddBondItemBuy', {
		templateUrl : 'module/bond/view/addBondItemBuy.view.htm',
		controller : 'ctrlViewAddBondItemBuy'
	});
	
});