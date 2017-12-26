/**
 * REVENUE.TIMELINE.MODULE
 */

var revenueTimelineModule = angular.module('revenue.timeline.module', ['revenue.timeline.config']);

revenueTimelineModule.controller('ctrlViewRevenueTimeline', function($scope, $routeParams, revenueTimelineService, logService, storageService, LOGTYPE, STORAGE_SERVICE_KEY, TIMELINE_LANGUAGE) {

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
			
			reqRevenueTimeline = {portfolioId : $scope.selectedPortfolio.id, depotId : [$scope.selectedDepot.id]};
			
	        break;
	    case 'bond':
			$scope.selectedPortfolio = storageService.get(STORAGE_SERVICE_KEY.PORTFOLIO);
			$scope.selectedDepot = storageService.get(STORAGE_SERVICE_KEY.DEPOT);
			$scope.selectedBond = storageService.get(STORAGE_SERVICE_KEY.BOND);
			
			bondIdList = [$scope.selectedBond.id];
			reqRevenueTimeline = {portfolioId : $scope.selectedPortfolio.id, depotList : [{depotId: $scope.selectedDepot.id, bondIdList : bondIdList}]};
	       
			break;
		} 
		
		return reqRevenueTimeline;		
	}

	// BUILD TIMELINE
	$scope.buildTimelineDates = function(resRevenue, startYear, endYear, view) {
		$scope.timeline = $scope.initTimeline(startYear, endYear, view);
		$scope.timeline = $scope.initRevenueTitle($scope.timeline, resRevenue);
		$scope.timeline = $scope.initRevenue($scope.timeline, resRevenue, startYear, endYear);
	}

	// REVENUE HEADER
	$scope.initRevenueTitle = function(timeline, resRevenue) {

		var depotList = resRevenue.depotList

		timeline['titleLine'] = {};
		timeline.titleLine = new Array();

		// TITLE DEPOT
		for (var d = 0, l1 = depotList.length; d < l1; d++) {
			timeline.titleLine.push({color : '', title : depotList[d].depotName});

			var bondList = depotList[d].bondList;

			// TITLE BOND
			for (var b = 0, l2 =  bondList.length; b < l2; b++) {
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

		for (var d = 0, l1 = depotList.length; d < l1; d++) {

			var bondList = depotList[d].bondList;

			timeline.valueLine[valueLineIndex] = new Array();
			timeline.valueLine[valueLineIndex].push({color : '', revenue : '\u00A0'});

			// BOND
			for (var b = 0, l2 = bondList.length; b < l2; b++) {

				// BOND TOTAL INTEREST RESULT
				var iterateDate = moment('01.01.' + startYear, 'DD.MM.YYYY');
				
				valueLineIndex++;
				timeline.valueLine[valueLineIndex] = new Array();

				while (+iterateDate.year() <= +endYear) {

					var list = bondList[b].bondTotalInterestResultList.slice(0, bondList[b].bondTotalInterestResultList.length);

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

	// HEADERS: YEAR, MONTH, WEEK, DATE
	$scope.initTimeline = function(startYear, endYear, view) {

		// init timeline
		var timeline = {};
		timeline['year'] = new Array();
		timeline['month'] = new Array();
		timeline['week'] = new Array();
		timeline['day'] = new Array();

		// add year to timeline
		var iterateDate = moment('01.01.' + startYear, 'DD.MM.YYYY');

		while (+iterateDate.year() <= +endYear) {

			var months = $scope.addMonthsToTimeline(timeline, iterateDate.year(), view);

			timeline = months.timeline;

			timeline.year.push({yearString : iterateDate.format('YYYY'), colspan : months.colspan});

			iterateDate.add(1, 'y');
		}

		return timeline;
	}
	
	$scope.addMonthsToTimeline = function(timeline, year, view) {

		var iterateDate = moment('01.01.' + year, 'DD.MM.YYYY');
		var colspan = 0;

		for (var m = 0; m <= 11; m++) {

			if(view == 'd')
			{
				var resultDay = $scope.addDaysToTimeline(timeline, year, iterateDate.format('MM'));
			}
			
			var resultWeek = $scope.addWeeksToTimeline(timeline, year, iterateDate.format('MM'));
			
			timeline = resultWeek.timeline;

			timeline.month.push({monthString : iterateDate.format('MM'), colspan : resultWeek.colspan});

			iterateDate.add(1, 'M');

			colspan = colspan + resultWeek.colspan;
		}
		
		return {timeline : timeline, colspan : colspan};
	}

	 $scope.addWeeksToTimeline = function(timeline, year, month){
		 			
		var iterateDate = moment('01' + month + year, 'DD.MM.YYYY');
		var compareWeek = iterateDate.format('WW');
		var colspanWeek = 0;
		var colspan = 0;

		for (var d = 1, l = iterateDate.daysInMonth(); d <= l; d++) {
			
			if (+iterateDate.format('WW') != compareWeek)
			{
				timeline['week'].push({weekString: compareWeek, colspan: colspanWeek});
				compareWeek = iterateDate.format('WW');
				colspanWeek = 0;
			}
			
			colspanWeek = colspanWeek + 1;
			colspan = colspan + 1;
			
			iterateDate.add(1, 'd');
		}
		
		timeline['week'].push({weekString: compareWeek, colspan: colspanWeek});
		compareWeek = iterateDate.format('WW');

		return {timeline : timeline, colspan : colspan};
	 }
	 
	$scope.addDaysToTimeline = function(timeline, year, month) {

		var iterateDate = moment('01' + month + year, 'DD.MM.YYYY');
		var colspan = iterateDate.daysInMonth();

		for (var d = 1; d <= colspan; d++) {
			timeline['day'].push(iterateDate.format('DD'));
			iterateDate.add(1, 'd');
		}

		return {timeline : timeline, colspan : colspan};
	}
	
	$scope.changeView = function()
	{
		 $scope.buildTimelineDates($scope.resRevenue, $scope.yearSlider.min, $scope.yearSlider.max, $scope.view);
	}

	// CONTROLLER INIT
	
	// EVENT: translate
	$scope.$emit('translate', {part : TIMELINE_LANGUAGE.PART});

	// EVENT: breadcrumb
	$scope.$emit('breadcrumb', {id : 'breadcrumb.revenue.timeline', link : '/viewRevenueTimeline'});
	
	// BUILD SCOPE REQUEST	
	var reqRevenueTimeline = $scope.buildScopeRequest($scope, $routeParams.scope);
	
	// INIT VIEW - DEFAULT DAYS
	$scope.view = 'd';

	// CALCULATE TIMELINE
	revenueTimelineService.getRevenueTimeline(
            function successCallback(response) {
		
            	$scope.resRevenue = response.data;
		
            	$scope.resRevenue.startYear = moment($scope.resRevenue.startDate).get('year');
            	$scope.resRevenue.endYear = moment($scope.resRevenue.endDate).get('year');
		
            	$scope.yearSlider = { min: $scope.resRevenue.startYear, max: $scope.resRevenue.endYear, 
			    options: { floor: $scope.resRevenue.startYear, 
						   ceil: $scope.resRevenue.endYear, 
						   showTicksValues: true,
						   onChange: function(id) {
					            $scope.buildTimelineDates($scope.resRevenue, $scope.yearSlider.min, $scope.yearSlider.max, $scope.view);
					        }
				         } };
			
		
            	$scope.buildTimelineDates($scope.resRevenue, $scope.resRevenue.startYear, $scope.resRevenue.endYear, $scope.view);
		
		}, 
		function errorCallback(response) {
			logService.set('Revenue.Timeline', LOGTYPE.ERROR, response.data);
			$scope.$emit('notify', {type:'E', msgId:'viewRevenueTimeline.notify.error'});
		},
		reqRevenueTimeline
		);
	
});
