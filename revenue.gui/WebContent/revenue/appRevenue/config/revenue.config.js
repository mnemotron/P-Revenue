/**
 * REVENUE.CONFIG
 */

var appRevenueConfig = angular.module('appRevenue.config',[
	    'ngRoute', 
	    'ngStorage',
		'pascalprecht.translate', 
		'storage.service',
		'breadcrumb.service',
		'portfolio.module'
		]);

appRevenueConfig.constant('REVENUE_LANGUAGE', {DEFAULT_LANGUAGE: 'de_DE', FILE_PREFIX: 'lang-', FILE_SUFFIX: '.json', PART: 'lang'});

appRevenueConfig.config(function($routeProvider, $translateProvider, $translatePartialLoaderProvider, REVENUE_LANGUAGE) {

	//translation
    $translatePartialLoaderProvider.addPart(REVENUE_LANGUAGE.PART);
    
    $translateProvider.useLoader('$translatePartialLoader', {
      urlTemplate: '{part}/'  + REVENUE_LANGUAGE.FILE_PREFIX + '{lang}' + REVENUE_LANGUAGE.FILE_SUFFIX
    });
    
	$translateProvider.preferredLanguage(REVENUE_LANGUAGE.DEFAULT_LANGUAGE);
	
//	$translateProvider.useSanitizeValueStrategy('sanitize');
																																																																																																					
	//navigation
	$routeProvider
	.when('/viewAbout', {
		templateUrl : 'view/about.view.htm'
	});

});