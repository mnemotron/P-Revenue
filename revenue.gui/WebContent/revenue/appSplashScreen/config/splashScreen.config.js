/**
 * SPLASHSCREEN.CONFIG
 */

var appSplashScreenConfig = angular.module('appSplashScreen.config',[
		'config.service',
		'log.service'
		]);

appSplashScreenConfig.config(function($routeProvider) {
  
//	//NAVIGATION
//	$routeProvider
//	.when('/viewAbout', {
//		templateUrl : 'view/about.view.htm',
//		controller : 'ctrlViewAbout'
//	});

});

//appRevenueConfig.config(['ngToastProvider', function(ngToast) {
//    ngToast.configure({
//      verticalPosition: 'bottom',
//      horizontalPosition: 'center',
//      maxNumber: 3,
//    })
//  }]);