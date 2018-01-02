/**
 * ACCOUNT.CONFIG
 */

var accountConfig = angular.module('account.config',[
	'ngRoute',
	'storage.service',
	'account.service',
	'log.service',
	]);

accountConfig.constant('ACCOUNT_LANGUAGE', {PART: 'module/account/lang'});

accountConfig.config(function($routeProvider){
	
	$routeProvider
	.when('/viewAccount', {
		templateUrl : 'module/account/view/account.view.htm',
		controller : 'ctrlViewAccount'
	});
	
});