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
		$scope.timeline = $scope.initTimeline(2017, 2020);
	}
	
	$scope.initTimeline = function(startYear, endYear) {
		
		//init timline
		var timeline = {};
		timeline['year'] = new Array();
		timeline['month'] = new Array();
		timeline['week'] = new Array();
		timeline['day'] = new Array();
		
		//add year to timeline
		var iterateDate = moment('01.01.' + startYear, 'DD.MM.YYYY');
		
		while (+iterateDate.year() <= +endYear) {
	
			var months = $scope.addMonthsToTimeline(timeline, iterateDate.year());
			
			timeline = months.timeline;
			
			timeline.year.push({yearString: iterateDate.format('YYYY'), colspan: months.colspan});
			
			iterateDate.add(1, 'y');
		}

		return timeline;
	}
	
	$scope.addMonthsToTimeline = function(timeline, year) {

		var iterateDate = moment('01.01.' + year, 'DD.MM.YYYY');
		var colspan = 0;
		
		for (var m = 0; m <= 11; m++) {
			
			var result = $scope.addDaysToTimeline(timeline, year, iterateDate.format('MM'));
//			var result  = $scope.addWeeksToTimeline(timeline, year, iterateDate.format('MM'));
			timeline = result.timeline;
			
			timeline.month.push({monthString: iterateDate.format('MM'), colspan: result.colspan});
			
			iterateDate.add(1, 'M');
			
			colspan = colspan + result.colspan;
		}
	
		return {timeline: timeline, colspan: colspan};
	}
	
//	$scope.addWeeksToTimeline = function(timeline, year, month){
//		
//		var iterateDate = moment('01' + month + year, 'DD.MM.YYYY').startOf('month');
//		var colspan = 4; //weeks in month
//		
//		for (var w = 0; w < 4; w++) {
//
//			timeline.week.push({weekString: iterateDate.format('WW'), colspan: 0});
//			
//			iterateDate.add(1, 'w');
//		}
//		
//		return {timeline: timeline, colspan: colspan};
//	}
	
	$scope.addDaysToTimeline = function(timeline, year, month) {
		
		var iterateDate = moment('01' + month + year, 'DD.MM.YYYY');
		var colspan = iterateDate.daysInMonth();
		
		for (var d = 1; d <= colspan; d++) {
			timeline['day'].push(iterateDate.format('DD'));
			iterateDate.add(1, 'd');
		}
		
		return {timeline: timeline, colspan: colspan};
	}

});

