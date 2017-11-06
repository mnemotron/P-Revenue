/**
 * REVENUE.TIMELINE.MODULE
 */

var revenueTimelineModule = angular.module('revenue.timeline.module', ['revenue.timeline.config']);

revenueTimelineModule.controller('ctrlViewRevenueTimeline', function($scope, $http, storageService, STORAGE_SERVICE_KEY) {
	
	//EVENT: translate
//	$scope.$emit('translate', {part:BOND_LANGUAGE.PART});
	
	//EVENT: breadcrumb
//	$scope.$emit('breadcrumb', {id:'breadcrumb.bond', link:'/viewBond'});

	//CALCULATE TIMELINE
	$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
	$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
	$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);
	
	var bondIdList = [$scope.selectedBond.id];
	var reqRevenueTimeline = {portfolioId: $scope.selectedPortfolio.id, depotId: $scope.selectedDepot.id, bondIdList: bondIdList};
	
	$http.post('http://localhost:8080/revenue.service/revenue/service/getRevenueTimeline', reqRevenueTimeline).then(function(response) {
		$scope.buildTimelineDates(response.data);	
	});
	
	//BUILD TIMELINE
	$scope.buildTimelineDates = function(data) {
	
		var startYear = 2017;
		var countYear = startYear;
		var count = 0;
		
		$scope.timeline = {};
		$scope.timeline['year'] = new Array();
		$scope.timeline['month'] = new Array();
		$scope.timeline['day'] = new Array();
			
		while (count <= 3) {
			
			var daysInYear = 0;
			
			var countMonth = new Date.parse('01.01.' + countYear);
			
			for (var m = 1; m <= 12; m++) {
//				$scope.timeline['month'].push(countMonth.toString('MM'));
				
				var monthString = countMonth.toString('MM');
								
				var monthTmp = countMonth.getMonth();
				var daysInMonth = Date.getDaysInMonth(countYear, monthTmp);
				
				daysInYear = daysInYear + daysInMonth;
				
			$scope.timeline.month.push({monthString: monthString, daysInMonth: daysInMonth});
				
				for (var d = 1; d <= daysInMonth; d++) {
					
					$scope.timeline['day'].push(d);
					
					
					
				}
				
				countMonth = countMonth.addMonths(1);
				
			}
			
			$scope.timeline.year.push({yearString: countYear, daysInYear: daysInYear});
			
			countYear = countYear + 1;
			
			count = count + 1;
		}
		
		//YEAR
		//MONTH + YEAR
		//KW (colspan=7) + MONTH + YEAR
		//DAY + KW + MONTH + YEAR
		//DEPOT
		//BOND + DATE (empty col + date)
		
	}

});

