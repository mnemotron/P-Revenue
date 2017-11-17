/**
 * REVENUE.TIMELINE.MODULE
 */

var revenueTimelineModule = angular.module('revenue.timeline.module', ['revenue.timeline.config']);

revenueTimelineModule.controller('ctrlViewRevenueTimeline', function($scope, $http, storageService, STORAGE_SERVICE_KEY) {
	
	//EVENT: translate
//	$scope.$emit('translate', {part:BOND_LANGUAGE.PART});
	
	//EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id:'breadcrumb.revenue.timeline', link:'/viewRevenueTimeline'});

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
		$scope.timeline = $scope.initTimeline(2018, 2025);
		$scope.timeline = $scope.initRevenue($scope.timeline, data, 2018, 2025);
	}
	
	$scope.initRevenue = function(timeline, resRevenue, startYear, endYear){
		
		var bondList = resRevenue.depotList[0].bondList;
		
		//BOND
		for (var b = 0; b < bondList.length; b++) {
			
		//BOND TOTAL INTEREST RESULT
		var iterateDate = moment('01.01.' + startYear, 'DD.MM.YYYY');
		timeline['bond'] = {};
		timeline.bond[b] = new Array();
		
		while (+iterateDate.year() <= +endYear) {
			
			var list = bondList[b].bondTotalInterestResultList;
			
			if (list.length > 0)
				{
				
				var tmpDate = moment(list[0].interestDate);
				
				if (iterateDate.isSame(tmpDate))
				{
				timeline.bond[b].push({color: 'bg-success', revenue: list[0].interestRevenue});
				list.shift();
				}
				else
					{
					timeline.bond[b].push({color: 'table-active', revenue: ''});
					}
				}
			else
				{
				timeline.bond[b].push({color: 'table-active', revenue: ''});
				}
			
			iterateDate.add(1, 'd');
			
		}
		
		}
		
		return timeline;
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

