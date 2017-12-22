/**
 * APPLICATION: REVENUE SPLASH SCREEN
 */

var appRevenueModule = angular.module('appSplashScreen', ['appSplashScreen.config']);

appRevenueModule.controller('ctrlSplashScreen', function($scope, $location, logService, configService, LOGTYPE, CONFIG_KEY) {

	// check, show splash screen?
	configService.getConfigSingle(function successCallback(response) {
		
			if (response.data.value == 'true')
			{
				$location.path('/viewSplashScreen');
			}
			else
			{
				window.location.href = "../appRevenue/revenue.index.html";	
			}

		}, function errorCallback(response) {

		}, {params : {key : CONFIG_KEY.SPLASHSCREEN}}
	);

});