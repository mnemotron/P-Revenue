/**
 * ACCOUNT.MODULE
 */

var accountModule = angular.module('account.module', ['account.config']);

accountModule.controller('ctrlViewAccount', function($scope, storageService, STORAGE_SERVICE_KEY, $location, accountService, logService, LOGTYPE, ACCOUNT_LANGUAGE) {

	// EVENT: translate
	$scope.$emit('translate', {part : ACCOUNT_LANGUAGE.PART});

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.account', link : '/viewAccount'});

});

accountModule.controller('ctrlViewCreateAccount', function($scope, storageService, logService, accountService, LOGTYPE, STORAGE_SERVICE_KEY, $location, ACCOUNT_LANGUAGE) {

	//EVENT: translate
	$scope.$emit('translate', {part:ACCOUNT_LANGUAGE.PART});
	
	$scope.createAccount = function() {

		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
		
		$scope.account['portfolioId'] = portfolio.id;
		
		accountService.createAccount(
				function successCallback(response){
					$location.path( '/viewPortfolio' );
				}, 
				function errorCallback(response){
					logService.set('Revenue.Account.Create', LOGTYPE.ERROR, response.data);
					$scope.$emit('notify', {type:'E', msgId:'viewCreateAccount.form.create.notify.error'});
				}, 
				$scope.account
		);
	}

});
