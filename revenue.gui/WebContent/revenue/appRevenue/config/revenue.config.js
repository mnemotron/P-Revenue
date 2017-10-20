/**
 * REVENUE.CONFIG
 */

var appRevenueConfig = angular.module('appRevenue.config',[
	    'ngStorage',
		'pascalprecht.translate', 
		'storage.service',
		'breadcrumb.service',
		'portfolio.module'
		]);

appRevenueConfig.constant('LANGUAGE', {DEFAULT_LANGUAGE: 'de_DE', FILE_PREFIX: 'lang/lang-', FILE_SUFFIX: '.json'});

appRevenueConfig.config(function($translateProvider, LANGUAGE) {

	$translateProvider.useStaticFilesLoader({
	prefix : LANGUAGE.FILE_PREFIX,
	suffix : LANGUAGE.FILE_SUFFIX
	});

	$translateProvider.preferredLanguage(LANGUAGE.DEFAULT_LANGUAGE);

	$translateProvider.useSanitizeValueStrategy('sanitize');

});