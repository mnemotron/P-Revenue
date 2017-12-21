/**
 * BOND.MODULE
 */

var bondModule = angular.module('bond.module', ['bond.config']);

bondModule.controller('ctrlViewBond', function($scope, $http, storageService, STORAGE_SERVICE_KEY, $location, bondService, logService, LOGTYPE, BOND_LANGUAGE) {

	// EVENT: translate
	$scope.$emit('translate', {part : BOND_LANGUAGE.PART});

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.bond', link : '/viewBond'});

	$scope.getBond = function() {

		$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);

		bondService.getBondItemBuyList(function successCallback(response) {
			$scope.bondItemBuyList = response.data

			for (var i = 0, l = $scope.bondItemBuyList.length; i < l; i++) {
				$scope.bondItemBuyList[i].buyDate = new Date($scope.bondItemBuyList[i].buyDate);
			}

		}, function errorCallback(response) {
			logService.set('Revenue.Bond.BondItemBuyList', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type : 'E', msgId : 'viewBond.bond.bondItemBuyList.notify.error'});
		}, {params : {portfolioId : $scope.selectedPortfolio.id, depotId : $scope.selectedDepot.id, bondId : $scope.selectedBond.id}});

		// $http.get('http://localhost:8080/revenue.service/bond/service/getBondItemBuyList',
		// {params : {portfolioId: $scope.selectedPortfolio.id, depotId:
		// $scope.selectedDepot.id, bondId:
		// $scope.selectedBond.id}}).then(function(response) {
		// $scope.bondItemBuyList = response.data
		//
		// for (var i = 0; i < $scope.bondItemBuyList.length; i++) {
		// $scope.bondItemBuyList[i].buyDate = new
		// Date($scope.bondItemBuyList[i].buyDate);
		// }
		// });

	}

	$scope.deleteBond = function() {

		bondService.deleteBond(function successCallback(response) {
			location.path('/viewDepot');
		}, function errorCallback(response) {
			logService.set('Revenue.Bond.Delete', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type : 'E', msgId : 'viewBond.bond.delete.notify.error'});
		}, {params : {bondId : $scope.selectedBond.id}});

		// $http.delete('http://localhost:8080/revenue.service/bond/service/deleteBond',
		// {params: {bondId : $scope.selectedBond.id}})
		// .then(function successCallback(response) {
		// $location.path( '/viewDepot' );
		//			  
		// },
		//		
		// function errorCallback(response) {
		//			
		// });
	}

	$scope.deleteBondItemBuy = function(index) {

		var bondItemBuyId = $scope.bondItemBuyList[index].id;

		bondService.deleteBondItemBuy(function successCallback(response) {
			$scope.getBond();
		}, function errorCallback(response) {
			logService.set('Revenue.Bond.BondItemBuy.Delete', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type : 'E', msgId : 'viewBond.bondItemBuy.delete.notify.error'});
		}, {params : {bondItemBuyId : bondItemBuyId}});

		// $http.delete('http://localhost:8080/revenue.service/bond/service/deleteBondItemBuy',
		// {params: {bondItemBuyId : bondItemBuyId}})
		// .then(function successCallback(response) {
		// $scope.getBond();
		// },
		//		
		// function errorCallback(response) {
		//			
		// });
	}

	$scope.getBond();

});

bondModule.controller('ctrlViewAddBond', function($locale, $scope, $http, storageService, STORAGE_SERVICE_KEY, $location, BOND_LANGUAGE) {

	// INIT
	$scope.dateFormat = $locale.DATETIME_FORMATS.shortDate

	// EVEN: translate
	$scope.$emit('translate', {part : BOND_LANGUAGE.PART});

	// EVENTLISTENER: localization change
	$scope.$on('$localeChangeSuccess', function(event, data) {
		$scope.dateFormat = $locale.DATETIME_FORMATS.shortDate;

	});

	// METHOD
	$scope.createBond = function() {

		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		var depot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);

		$scope.bond['portfolioId'] = portfolio.id;
		$scope.bond['depotId'] = depot.id;

		bondService.createBond(function successCallback(response) {
			$location.path('/viewDepot');
		}, function errorCallback(response) {
			logService.set('Revenue.Bond.Create', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type : 'E', msgId : 'viewAddBond.form.button.add.notify.error'});
		}, 
		$scope.bond
	);

		// $http.post('http://localhost:8080/revenue.service/bond/service/createBond',
		// $scope.bond)
		//
		// .then(function successCallback(response) {
		// $location.path('/viewDepot');
		// },
		//
		// function errorCallback(response) {
		//
		// });
	}

});

bondModule.controller('ctrlViewAddBondItemBuy', function($locale, $scope, $http, storageService, STORAGE_SERVICE_KEY, $location, BOND_LANGUAGE) {

	// INIT
	$scope.dateFormat = $locale.DATETIME_FORMATS.shortDate

	// EVEN: translate
	$scope.$emit('translate', {part : BOND_LANGUAGE.PART});

	// EVENTLISTENER: localization change
	$scope.$on('$localeChangeSuccess', function(event, data) {
		$scope.dateFormat = $locale.DATETIME_FORMATS.shortDate;

	});

	// METHOD
	$scope.createBondItemBuy = function() {

		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		var depot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
		var bond = storageService.get(STORAGE_SERVICE_KEY.BOND);

		$scope.bondItemBuy['portfolioId'] = portfolio.id;
		$scope.bondItemBuy['depotId'] = depot.id;
		$scope.bondItemBuy['bondId'] = bond.id;

		bondService.createBondItemBuy(
         function successCallback(response) {
        	 $location.path('/viewBond');
         }, 
         function errorCallback(response) {
			logService.set('Revenue.Bond.BondItemBuy.Create', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type : 'E', msgId : 'viewAddBondItemBuy.form.button.buy.notify.error'});
		}, 
		$scope.bondItemBuy
	);

		// $http.post('http://localhost:8080/revenue.service/bond/service/createBondItemBuy',
		// $scope.bondItemBuy)
		//
		// .then(function successCallback(response) {
		// $location.path('/viewBond');
		// },
		//
		// function errorCallback(response) {
		//
		// });

	}

});
