/**
 * SHARE.MODULE
 */

var shareModule = angular.module('share.module', ['share.config']);

shareModule.controller('ctrlViewShare', function($scope, storageService, STORAGE_SERVICE_KEY, $location, shareService, logService, LOGTYPE, SHARE_LANGUAGE) {

	// EVENT: translate
	$scope.$emit('translate', {part : SHARE_LANGUAGE.PART});

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.share', link : '/viewShare'});
	
	$scope.selectedShare = storageService.get(STORAGE_SERVICE_KEY.SHARE);
	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);

	$scope.deleteShare = function(){
		
//		accountService.deleteAccount(
//				function successCallback(response){
//					 $location.path( '/viewPortfolio' );
//				}, 
//				function errorCallback(response){
//					logService.set('Revenue.Account.Delete', LOGTYPE.ERROR, response.data);
//					$scope.$emit('notify', {type:'E', msgId:'viewAccount.account.delete.notify.error'});
//				},
//				{params: {portfolioId : $scope.selectedPortfolio.id, id : $scope.selectedAccount.id}}
//		);

	}
	
});

shareModule.controller('ctrlViewCreateShare', function($scope, storageService, logService, shareService, LOGTYPE, STORAGE_SERVICE_KEY, $location, SHARE_LANGUAGE) {

//	//EVENT: translate
//	$scope.$emit('translate', {part:ACCOUNT_LANGUAGE.PART});
//	
//	$scope.createAccount = function() {
//
//		var portfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
//		
//		$scope.account['portfolioId'] = portfolio.id;
//		
//		accountService.createAccount(
//				function successCallback(response){
//					$location.path( '/viewPortfolio' );
//				}, 
//				function errorCallback(response){
//					logService.set('Revenue.Account.Create', LOGTYPE.ERROR, response.data);
//					$scope.$emit('notify', {type:'E', msgId:'viewCreateAccount.form.create.notify.error'});
//				}, 
//				$scope.account
//		);
//	}

});
