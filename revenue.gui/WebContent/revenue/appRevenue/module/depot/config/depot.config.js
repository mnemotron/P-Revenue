/**
 * DEPOT.CONFIG
 */

var depotConfig = angular.module('depot.config',[
	'ngRoute',
	'storage.service',
	'depot.service',
	'share.service',
	'bond.service',
	'log.service',
	'share.module',
	'bond.module'
	]);

depotConfig.constant('DEPOT_LANGUAGE', {PART: 'module/depot/lang'});

depotConfig.config(function($routeProvider){
	
	$routeProvider
	.when('/viewDepot', {
		templateUrl : 'module/depot/view/depot.view.htm',
		controller : 'ctrlViewDepot'
	}).when('/viewAddShare', {
		templateUrl : 'module/share/view/addShare.view.htm',
		controller : 'ctrlViewAddShare'
	}).when('/viewAddBond', {
		templateUrl : 'module/bond/view/addBond.view.htm',
		controller : 'ctrlViewAddBond'
	}).when('/viewRevenueTimeline/:scope', {
		templateUrl : 'module/revenueTimeline/view/revenueTimeline.view.htm',
		controller : 'ctrlViewRevenueTimeline'
	});
	
});