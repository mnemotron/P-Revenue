/**
 * REVENUE.CONFIG
 */

var appRevenueConfig = angular.module('appRevenue.config',[
	    'ngRoute', 
	    'ngStorage',
		'pascalprecht.translate', 
		'storage.service',
		'breadcrumb.service',
		'portfolio.module',
		'dateParser',
		'tmh.dynamicLocale'
		]);

appRevenueConfig.constant('REVENUE_LANGUAGE', {DEFAULT_LANGUAGE: 'de', FILE_PREFIX: 'lang-', FILE_SUFFIX: '.json', PART: 'lang'});

appRevenueConfig.config(function($routeProvider, $translateProvider, $translatePartialLoaderProvider, REVENUE_LANGUAGE, $dateParserProvider, tmhDynamicLocaleProvider) {

	//TRANSLATION
    $translatePartialLoaderProvider.addPart(REVENUE_LANGUAGE.PART);
    
    $translateProvider.useLoader('$translatePartialLoader', {
      urlTemplate: '{part}/'  + REVENUE_LANGUAGE.FILE_PREFIX + '{lang}' + REVENUE_LANGUAGE.FILE_SUFFIX
    });
    
	$translateProvider.preferredLanguage(REVENUE_LANGUAGE.DEFAULT_LANGUAGE);
	
	//$translateProvider.useSanitizeValueStrategy('sanitize');
	
	//LOCALIZATION
	 $dateParserProvider.watchLocale(true);
     tmhDynamicLocaleProvider.localeLocationPattern('../lib/angular-1.6.6/locale/angular-locale_{{locale}}.js');
     
	//NAVIGATION
	$routeProvider
	.when('/viewAbout', {
		templateUrl : 'view/about.view.htm',
		controller : 'ctrlViewAbout'
	}).when('/viewPreferences', {
		templateUrl : 'view/preferences.view.htm',
		controller : 'ctrlViewPreferences'
	});

});