/**
 * APPLICATION: REVENUE
 */

var appRevenueModule = angular.module('appRevenue', ['ngSanitize', 'appRevenue.config']);

appRevenueModule.run(function($rootScope, $translate) {
	$rootScope.$on('$translatePartialLoaderStructureChanged', function() {
		$translate.refresh();
	});
});

appRevenueModule.controller('ctrlRevenue', function($scope, $sce, $http, $translate, ngToast, breadcrumbService, $translatePartialLoader, tmhDynamicLocale, LANGUAGE_FILE, CONFIG_KEY) {

	// EVENTLISTENER: breadcrumb from children
	$scope.$on('breadcrumb', function(event, data) {

		breadcrumbService.set(data);

		$scope.breadcrumb = breadcrumbService.get();

	});

	// EVENTLISTENER: translate from children
	$scope.$on('translate', function(event, data) {
		$translatePartialLoader.addPart(data.part);
	});
	
	// EVENTLISTENDER: notify
	$scope.$on('notify', function(event, data){
		
		switch (data.type) {
			case 'S' :
				ngToast.create({
					  className: 'success',
					  compileContent: true,
					  content: $sce.trustAsHtml('<div translate="'+data.msgId+'"></div>')
					});
				break;
				
			case 'E' :
				ngToast.create({
					  className: 'danger',
					  dismissOnTimeout: false,
					  compileContent: true,
					  dismissButton: false,
					  content: $sce.trustAsHtml('<div><a href="#!viewLog" translate="'+data.msgId+'"></a></div>')
					});
				break;

			default :
				ngToast.create({
					  className: 'info',
					  compileContent: true,
					  content: $sce.trustAsHtml('<div translate="'+data.msgId+'"></div>')
					});
				break;
		}

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
	
	// INIT CONFIGURATION
	$scope.initConfig = function(config){
		
		for (var i = 0, l = config.length; i < l; i++) {

			switch (config[i].key) {
				case CONFIG_KEY.LANGUAGE:
					$scope.configLanguage = config[i].value;
					break;

				case CONFIG_KEY.CURRENCY :
					$scope.configCurrency = config[i].value
					break;
			}
		}
		
		$scope.changeLang($scope.configLanguage.toLowerCase());
	}

	// GET CONFIGURATION
	$http.get('http://localhost:8080/revenue.service/config/service/getConfig')
		.then(function successCallback(response) {
			$scope.initConfig(response.data);
		}, 
	
		function errorCallback(response) {
			$scope.initDefaultLocale(LANGUAGE_FILE.DEFAULT_LANGUAGE);
			$scope.$emit('notify', {type:'RAW', raw: response.data});	
		});
});

appRevenueModule.controller('ctrlViewLog', function($scope, logService) {
	
	$scope.log = logService.get();
	
	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.log', link : '/viewLog'});
	
});

appRevenueModule.controller('ctrlViewAbout', function($scope) {

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.about', link : '/viewAbout'});

});

appRevenueModule.controller('ctrlViewPreferences', function($scope, $http, $translate, logService, configService, LOGTYPE, CONFIG_KEY, LANGUAGE) {
	
	$scope.savePreferences = function(){
		
		var config = new Array();
		
		config.push({key: CONFIG_KEY.LANGUAGE, value: $scope.configLanguage});
		
//		configService.updateConfig(
//				
//		);
		
		$http.put('http://localhost:8080/revenue.service/config/service/updateConfig', config)
			.then(function successCallback(response) {
				$scope.$emit('notify', {type:'S', msgId:'preferences.menu.save.notify.success'});	
		}, 
		function errorCallback(response) {
				logService.set('Revenue', LOGTYPE.ERROR, response.data);
				$scope.$emit('notify', {type:'E', msgId:'preferences.menu.save.notify.error'});	
		});
	}
	
	$scope.isDefaultLanguage = function(language){
		return ((language == $scope.configLanguage) ? true : false);
	}
	
	// LANGUAGE: INIT OPTIONS
	$scope.languages = [{key: LANGUAGE.EN, textId: 'preferences.language.defaultLanguage.en', selected: $scope.isDefaultLanguage(LANGUAGE.EN)}, {key: LANGUAGE.DE, textId: 'preferences.language.defaultLanguage.de', selected: $scope.isDefaultLanguage(LANGUAGE.DE)}];
	
	// CURRENCY: INIT OPTIONS
	$scope.currencies = [{key: $scope.configCurrency, textId: 'preferences.currency.defaultCurrency.eur', selected: $scope.configCurrency}];
	
	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.preferences', link : '/viewPreferences'});
});