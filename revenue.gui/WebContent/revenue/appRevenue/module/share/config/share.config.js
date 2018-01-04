/**
 * SHARE.CONFIG
 */

var shareConfig = angular.module('share.config',[
	'ngRoute',
	'storage.service',
	'share.service',
	'log.service',
	'chart.js'
	]);

shareConfig.constant('SHARE_LANGUAGE', {PART: 'module/share/lang'});

shareConfig.config(function($routeProvider){
	
	$routeProvider
	.when('/viewShare', {
		templateUrl : 'module/share/view/share.view.htm',
		controller : 'ctrlViewShare'
	});
	
});