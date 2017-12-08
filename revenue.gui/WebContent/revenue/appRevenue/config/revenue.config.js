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

appRevenueConfig.constant('LANGUAGE_FILE', 
    { 	DEFAULT_LANGUAGE: 'en', 
	  	FILE_PREFIX: 'lang-', 
	  	FILE_SUFFIX: '.json', 
	  	PART: 'lang'
    });

appRevenueConfig.constant('CONFIG_KEY',
    {
      	LANGUAGE: 'LANGUAGE',
      	CURRENCY: 'CURRENCY'
    });

appRevenueConfig.constant('LANGUAGE',
    {
		EN: 'EN',
		DE: 'DE'
    });

appRevenueConfig.config(function($routeProvider, $translateProvider, $translatePartialLoaderProvider, LANGUAGE_FILE, $dateParserProvider, tmhDynamicLocaleProvider) {

	//TRANSLATION
    $translatePartialLoaderProvider.addPart(LANGUAGE_FILE.PART);
    
    $translateProvider.useLoader('$translatePartialLoader', {
      urlTemplate: '{part}/'  + LANGUAGE_FILE.FILE_PREFIX + '{lang}' + LANGUAGE_FILE.FILE_SUFFIX
    });
    
	$translateProvider.preferredLanguage(LANGUAGE_FILE.DEFAULT_LANGUAGE);
	
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