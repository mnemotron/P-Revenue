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
		$scope.timeline = $scope.initTimeline(2017, 2017);
	}
	
	$scope.initTimeline = function(startYear, endYear) {
		
		//init timline
		var timeline = {};
		timeline['year'] = new Array();
		timeline['month'] = new Array();
		timeline['week'] = new Array();
		timeline['day'] = new Array();
		
		//add year to timeline
		var iterateYearDate = new Date.parse('01.01.' + startYear);
		
		while (+iterateYearDate.getFullYear() <= +endYear) {
	
			timeline = $scope.addMonthsToTimeline(timeline, iterateYearDate.getFullYear());
			
//			timline = $scope.addWeeksToTimeline(timeline, iterateYearDate.getFullYear(), 7);
			
			iterateYearDate = iterateYearDate.addYears(1);
		}

		return timeline;
	}
	
	$scope.addMonthsToTimeline = function(timeline, year) {

		var iterateMonthDate = new Date.parse('01.01.' + year);
		var daysInYear = 0;
		
		for (var m = 0; m <= 11; m++) {
			
			var daysInMonth = Date.getDaysInMonth(year, m);
			
			$scope.addDaysToTimeline(timeline, year, daysInMonth);
			
			timeline.month.push({monthString: iterateMonthDate.toString('MM'), daysInMonth: daysInMonth});
			
			iterateMonthDate = iterateMonthDate.addMonths(1);
			
			daysInYear = daysInYear + daysInMonth;
		}
		
		timeline.year.push({yearString: year, daysInYear: daysInYear});
	
		return timeline;
	}
	
	$scope.addWeeksToTimeline = function(timeline, year, daysInWeek){
		
		var iterateWeekDate = new Date.parse('01.01.' + year);
		
		for (var w = 1; w <= 52 ; w++) {
			

			
			timeline.week.push({weekString: iterateWeekDate.getISOWeek(), daysInWeek: 7});
			
			iterateWeekDate = iterateWeekDate.addWeeks(1);
		}
		
		return timeline;
	}
	
	$scope.addDaysToTimeline = function(timeline, year, daysInMonth) {
		
		var iterateDayDate = new Date.parse('01.01.' + year);
		
		for (var d = 1; d <= daysInMonth; d++) {
			timeline['day'].push(iterateDayDate.toString('dd'));
			iterateDayDate = iterateDayDate.addDays(1);
		}
		
		return timeline;
	}

});

