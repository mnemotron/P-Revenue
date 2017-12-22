/**
 * SPLASHSCREEN.CONFIG
 */

var appSplashScreenConfig = angular.module('appSplashScreen.config',[
	    'ngRoute',
		'config.service',
		'log.service'
		]);

appSplashScreenConfig.config(function($routeProvider) {
  
	//NAVIGATION
	$routeProvider
	.when('/viewSplashScreen', {
		templateUrl : 'view/splashScreen.view.htm'
	});

});