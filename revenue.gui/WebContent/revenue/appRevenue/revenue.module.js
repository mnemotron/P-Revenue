/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.run(function($rootScope, $translate) {
	$rootScope.$on('$translatePartialLoaderStructureChanged', function() {
		$translate.refresh();
	});
});

appRevenueModule.controller('ctrlRevenue', function($scope, $http, $translate, breadcrumbService, $translatePartialLoader, tmhDynamicLocale, LANGUAGE_FILE, CONFIG_KEY) {

	// EVENTLISTENER: breadcrumb from children
	$scope.$on('breadcrumb', function(event, data) {

		breadcrumbService.set(data);

		$scope.breadcrumb = breadcrumbService.get();

	});

	// EVENTLISTENER: translate from children
	$scope.$on('translate', function(event, data) {
		$translatePartialLoader.addPart(data.part);
	});

	// LANGUAGE AND LOCALIZATION
	$scope.changeLang = function(key) {

		$translate.use(key);

		$scope.breadcrumb = breadcrumbService.get();

		$scope.locale = key;

	};

	// LOCALIZATION
	$scope.$watch('locale', function(value, oldValue) {
		if (value !== oldValue) {
			tmhDynamicLocale.set(value);
		}
	});

	// INIT DEFAULTS
	$scope.initDefaultLocale = function(language) {
		$scope.locale = language;
		tmhDynamicLocale.set(language);
	}
	
	// INIT CONFIGURATION
	$scope.initConfig = function(config){
		
		for (let i = 0; i < config.length; i++) {

			switch (config[i].key) {
				case CONFIG_KEY.LANGUAGE:
					$scope.configLanguage = config[i].value;
					break;

				case CONFIG_KEY.CURRENCY :
					$scope.configCurrency = config[i].value
					break;
			}
		}
		
		$scope.initDefaultLocale($scope.configLanguage.toLowerCase());
	}

	// GET CONFIGURATION
	$http.get('http://localhost:8080/revenue.service/config/service/getConfig')
		.then(function successCallback(response) {
			$scope.initConfig(response.data);
		}, 
	
		function errorCallback(response) {
			$scope.initDefaultLocale(LANGUAGE_FILE.DEFAULT_LANGUAGE);
		});
});

appRevenueModule.controller('ctrlViewAbout', function($scope) {

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.about', link : '/viewAbout'});

});

appRevenueModule.controller('ctrlViewPreferences', function($scope, $http, $translate, CONFIG_KEY, LANGUAGE) {
	
	$scope.savePreferences = function(){
		
		var config = new Array();
		
		config.push({key: CONFIG_KEY.LANGUAGE, value: $scope.config.language});
		
		$http.put('http://localhost:8080/revenue.service/config/service/updateConfig', config)
			.then(function successCallback(response) {
				
		}, 
		function errorCallback(response) {
			
		});
	}
	
	$scope.isDefaultLanguage = function(language){
		return ((language == $scope.configLanguage) ? true : false);
	}
	
	// LANGUAGE: INIT OPTIONS
	$scope.languages = [{key: LANGUAGE.EN, textId: 'preferences.language.defaultLanguage.en', selected: $scope.isDefaultLanguage(LANGUAGE.EN)}, {key: LANGUAGE.DE, textId: 'preferences.language.defaultLanguage.de', selected: $scope.isDefaultLanguage(LANGUAGE.DE)}];
	
	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.preferences', link : '/viewPreferences'});
});