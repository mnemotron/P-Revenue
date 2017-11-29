/**
 * REVENUE.TIMELINE.MODULE
 */

var revenueTimelineModule = angular.module('revenue.timeline.module', ['revenue.timeline.config']);

revenueTimelineModule.controller('ctrlViewRevenueTimeline', function($scope, $http, $routeParams, storageService, STORAGE_SERVICE_KEY, TIMELINE_LANGUAGE) {

	// FUNCTIONS
	
	// BUILD SCOPE REQUEST
	$scope.buildScopeRequest = function($scope, scope){
		
		var reqRevenueTimeline;
		var bondIdList;

		switch(scope) {
	    case 'portfolio':
			$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
			
			reqRevenueTimeline = {portfolioId : $scope.selectedPortfolio.id};
	       
			break;
	    case 'depot':
			$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
			$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
			
			reqRevenueTimeline = {portfolioId : $scope.selectedPortfolio.id, depotId : $scope.selectedDepot.id};
			
	        break;
	    case 'bond':
			$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
			$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
			$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);
			
			bondIdList = [$scope.selectedBond.id];
			reqRevenueTimeline = {portfolioId : $scope.selectedPortfolio.id, depotId : $scope.selectedDepot.id, bondIdList : bondIdList};
	       
			break;
		} 
		
		return reqRevenueTimeline;		
	}

	// BUILD TIMELINE
	$scope.buildTimelineDates = function(data) {
		$scope.timeline = $scope.initTimeline(2018, 2018);
		$scope.timeline = $scope.initRevenueTitle($scope.timeline, data);
		$scope.timeline = $scope.initRevenue($scope.timeline, data, 2018, 2018);
	}

	// REVENUE HEADER
	$scope.initRevenueTitle = function(timeline, resRevenue) {

		var depotList = resRevenue.depotList

		timeline['titleLine'] = {};
		timeline.titleLine = new Array();

		// TITLE DEPOT
		for (var d = 0; d < depotList.length; d++) {
			timeline.titleLine.push({color : '', title : depotList[d].depotName});

			var bondList = depotList[d].bondList;

			// TITLE BOND
			for (var b = 0; b < bondList.length; b++) {
				timeline.titleLine.push({color : 'table-active', title : bondList[b].bondName});
			}

		}

		return timeline;
	}

	// REVENUE VALUES
	$scope.initRevenue = function(timeline, resRevenue, startYear, endYear) {

		var valueLineIndex = 0;
		timeline['valueLine'] = {};

		// DEPOT
		var depotList = resRevenue.depotList;

		for (var d = 0; d < depotList.length; d++) {

			var bondList = depotList[d].bondList;

			timeline.valueLine[valueLineIndex] = new Array();
			timeline.valueLine[valueLineIndex].push({color : '', revenue : '\u00A0'});

			// BOND
			for (var b = 0; b < bondList.length; b++) {

				// BOND TOTAL INTEREST RESULT
				var iterateDate = moment('01.01.' + startYear, 'DD.MM.YYYY');
				
				valueLineIndex++;
				timeline.valueLine[valueLineIndex] = new Array();

				while (+iterateDate.year() <= +endYear) {

					var list = bondList[b].bondTotalInterestResultList;

					if (list.length > 0) {

						var tmpDate = moment(list[0].interestDate);
						
						while (tmpDate.isBefore(iterateDate, 'year')) {
							list.shift();
							tmpDate = moment(list[0].interestDate);
						}

						if (iterateDate.isSame(tmpDate)) {
							timeline.valueLine[valueLineIndex].push({color : 'bg-success', revenue : list[0].interestRevenue});
							list.shift();
						} else {
							timeline.valueLine[valueLineIndex].push({color : 'table-active', revenue : ''});
						}
					} else {
						timeline.valueLine[valueLineIndex].push({color : 'table-active', revenue : ''});
					}

					iterateDate.add(1, 'd');

				}

			}
			
			valueLineIndex++;
		}

		return timeline;
	}

	// HEADERS: YEAR, MONTH, WEEK; DATE
	$scope.initTimeline = function(startYear, endYear) {

		// init timline
		var timeline = {};
		timeline['year'] = new Array();
		timeline['month'] = new Array();
		timeline['week'] = new Array();
		timeline['day'] = new Array();

		// add year to timeline
		var iterateDate = moment('01.01.' + startYear, 'DD.MM.YYYY');

		while (+iterateDate.year() <= +endYear) {

			var months = $scope.addMonthsToTimeline(timeline, iterateDate.year());

			timeline = months.timeline;

			timeline.year.push({yearString : iterateDate.format('YYYY'), colspan : months.colspan});

			iterateDate.add(1, 'y');
		}

		return timeline;
	}

	$scope.addMonthsToTimeline = function(timeline, year) {

		var iterateDate = moment('01.01.' + year, 'DD.MM.YYYY');
		var colspan = 0;

		for (var m = 0; m <= 11; m++) {

			var result = $scope.addDaysToTimeline(timeline, year, iterateDate.format('MM'));
			// var result = $scope.addWeeksToTimeline(timeline, year,
			// iterateDate.format('MM'));
			timeline = result.timeline;

			timeline.month.push({monthString : iterateDate.format('MM'), colspan : result.colspan});

			iterateDate.add(1, 'M');

			colspan = colspan + result.colspan;
		}

		return {timeline : timeline, colspan : colspan};
	}

	// $scope.addWeeksToTimeline = function(timeline, year, month){
	//		
	// var iterateDate = moment('01' + month + year,
	// 'DD.MM.YYYY').startOf('month');
	// var colspan = 4; //weeks in month
	//		
	// for (var w = 0; w < 4; w++) {
	//
	// timeline.week.push({weekString: iterateDate.format('WW'), colspan: 0});
	//			
	// iterateDate.add(1, 'w');
	// }
	//		
	// return {timeline: timeline, colspan: colspan};
	// }

	$scope.addDaysToTimeline = function(timeline, year, month) {

		var iterateDate = moment('01' + month + year, 'DD.MM.YYYY');
		var colspan = iterateDate.daysInMonth();

		for (var d = 1; d <= colspan; d++) {
			timeline['day'].push(iterateDate.format('DD'));
			iterateDate.add(1, 'd');
		}

		return {timeline : timeline, colspan : colspan};
	}
	
	
	// CONTROLLER INIT
	
	// EVENT: translate
	$scope.$emit('translate', {part : TIMELINE_LANGUAGE.PART});

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.revenue.timeline', link : '/viewRevenueTimeline'});
	
	// BUILD SCOPE REQUEST
	var reqRevenueTimeline = $scope.buildScopeRequest($scope, $routeParams.scope);

	// CALCULATE TIMELINE
	$http.post('http://localhost:8080/revenue.service/revenue/service/getRevenueTimeline', reqRevenueTimeline).then(function(response) {
		$scope.buildTimelineDates(response.data);
	});

});
