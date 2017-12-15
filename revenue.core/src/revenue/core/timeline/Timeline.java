package revenue.core.timeline;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import revenue.core.timeline.entity.TimelineBondHeader;
import revenue.core.timeline.entity.TimelineBondInterest;
import revenue.core.timeline.entity.TimelineBondItem;
import revenue.core.timeline.entity.TimelineDepot;
import revenue.core.timeline.entity.TimelinePortfolio;
import revenue.core.timeline.util.ComparatorDateForBondInterestResult;
import revenue.core.timeline.util.ComparatorDateForBondItemBuy;
import revenue.entity.BondHeader;
import revenue.entity.BondItemBuy;
import revenue.entity.Depot;
import revenue.entity.Interest;
import revenue.entity.Portfolio;

public class Timeline {

	// TODO: Stückzinsen: Stückzins = Nominalwert × Zinssatz × Tage/360
	private static final int MONTH_OF_YEAR = 12;
	
	private Portfolio portfolio;
	
	private TimelinePortfolio portfolioResult;

	public Timeline() {
	}

	public Timeline(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public TimelinePortfolio getResult() {
		return portfolioResult;
	}

	public void calTimeline() {
		
		this.portfolioResult = new TimelinePortfolio();
		
		// depot
		ArrayList<TimelineDepot> locDepotResult = this.calTimelineDepot(new ArrayList<Depot>(this.portfolio.getDepot()));
		
		this.portfolioResult.setBondDepotResult(locDepotResult);
	}

	private ArrayList<TimelineDepot> calTimelineDepot(ArrayList<Depot> depotList) {
		
		ArrayList<TimelineDepot> locTimelineDepot = new ArrayList<TimelineDepot>();
		
		for (Depot depot : depotList) {

			TimelineDepot locDepotResult = new TimelineDepot();
			
			// bond header
			ArrayList<TimelineBondHeader> locBondHeaderResultList = this.calTimelineBondHeader(new ArrayList<BondHeader>(depot.getBondHeader()));
			locDepotResult.setBondHeaderResult(locBondHeaderResultList);
			locDepotResult.setDepot(depot);
			
			locTimelineDepot.add(locDepotResult);
		}
		
		return locTimelineDepot;
	}

	private ArrayList<TimelineBondHeader> calTimelineBondHeader(ArrayList<BondHeader> bondHeaderList) {
		
		ArrayList<TimelineBondHeader> locBondHeaderResultList = new ArrayList<TimelineBondHeader>();
		
		for (BondHeader bondHeader : bondHeaderList) {

			TimelineBondHeader locBondHeaderResult = new TimelineBondHeader();
			locBondHeaderResult.setBondHeader(bondHeader);
			
			// bond item buy
			ArrayList<TimelineBondItem> locBondItemResultList = this.calInterestBondItem(bondHeader, new ArrayList<BondItemBuy>(bondHeader.getBondItemBuy()));
			locBondHeaderResult.setBondItemsResult(locBondItemResultList);
			
			// total bond item buy
			ArrayList<TimelineBondInterest> locTotalInterestBondItemResultList = this.calTotalInterestBondItem(locBondItemResultList);
			locBondHeaderResult.setBondTotalInterestResult(locTotalInterestBondItemResultList);
			
			// start/end date
			locBondHeaderResult = calStartEndDate(locBondHeaderResult);
			
			locBondHeaderResultList.add(locBondHeaderResult);
		}
		
		return locBondHeaderResultList;
	}

	private ArrayList<TimelineBondItem> calInterestBondItem(BondHeader bondHeader, ArrayList<BondItemBuy> bondItemBuyList) {

		ArrayList<TimelineBondItem> locBondItemResultList = new ArrayList<TimelineBondItem>();
		
		// get interest
		ArrayList<Interest> locInterestList = new ArrayList<Interest>(bondHeader.getInterest());

		double locInterestPerYear = locInterestList.get(0).getInterest();

		// get due date
		Date locDueDate = bondHeader.getDueDate();

		// get interest date
		Date locInterestDate = bondHeader.getInterestDate();

		// get interest intervall
		byte locInterestIntervall = bondHeader.getInterestIntervall();

		// calculate revenue for each bond item
		for (BondItemBuy bondItemBuy : bondItemBuyList) {

			TimelineBondItem locBondItemResult = new TimelineBondItem();
			locBondItemResult.setBondItemBuy(bondItemBuy);
			
			ArrayList<TimelineBondInterest> locBondInterestDates = new ArrayList<TimelineBondInterest>();

			Date locBuyDate = bondItemBuy.getBuyDate();

			// determine interest dates based on buy date
			ArrayList<Date> locNextInterestDates = this.getNextInterestDates(locBuyDate, locInterestDate, locDueDate, locInterestIntervall);

			for (Date interestDate : locNextInterestDates) 
			{
				
				int locDaysPerYear = this.calDaysPerYear(interestDate);

				double locReturnPerYearIntervall = this.calReturnPerYearWithIntervall(
						bondItemBuy.getNominalValue(), locInterestPerYear, locInterestIntervall,
						locDaysPerYear);

				TimelineBondInterest locBondInterestDate = new TimelineBondInterest();
				locBondInterestDate.setInterestDate(interestDate);
				locBondInterestDate.setInterest(locReturnPerYearIntervall);

				locBondInterestDates.add(locBondInterestDate);

			}

			locBondItemResult.setBondInterestDates(locBondInterestDates);
			locBondItemResultList.add(locBondItemResult);
		}
		
		return locBondItemResultList;
	}
	
	private ArrayList<TimelineBondInterest> calTotalInterestBondItem(ArrayList<TimelineBondItem> bondItemResultList) 
	{
			HashMap<String, TimelineBondInterest> locBondTotalInterestHash = new HashMap<String, TimelineBondInterest>();

			for (TimelineBondItem bondItemResult : bondItemResultList) {

				ArrayList<TimelineBondInterest> locBondInterestResult = bondItemResult.getBondInterestDates();

				for (TimelineBondInterest bondInterestResult : locBondInterestResult) {

					if (locBondTotalInterestHash
							.containsKey(bondInterestResult.getInterestDate().toInstant().toString())) {
						// update value
						TimelineBondInterest locBondIR = locBondTotalInterestHash
								.get(bondInterestResult.getInterestDate().toInstant().toString());

						double locSum = bondInterestResult.getInterest() + locBondIR.getInterest();

						locBondIR.setInterest(locSum);

						locBondTotalInterestHash.put(bondInterestResult.getInterestDate().toInstant().toString(),
								locBondIR);
					} else {
						// insert new key and value
						TimelineBondInterest locValue = new TimelineBondInterest();
						locValue.setInterest(bondInterestResult.getInterest());
						locValue.setInterestDate(bondInterestResult.getInterestDate());

						locBondTotalInterestHash.put(bondInterestResult.getInterestDate().toInstant().toString(),
								locValue);
					}

				}

			}

			ArrayList<TimelineBondInterest> locBondTotalInterestList = new ArrayList<TimelineBondInterest>(
					locBondTotalInterestHash.values());

			// sort items ascending by interest date
			Collections.sort(locBondTotalInterestList, new ComparatorDateForBondInterestResult());

			
			return locBondTotalInterestList;
	}


	private ArrayList<Date> getNextInterestDates(Date buyDate, Date interestDate, Date dueDate, byte interestIntervall) {

		ArrayList<Date> locInterestDates = new ArrayList<Date>();

		int locMonthIntervall = calMonthsIntervall(interestIntervall);

		Calendar locCalIntDate = Calendar.getInstance();
		Calendar locCalBuyDate = Calendar.getInstance();
		Calendar locCalDueDate = Calendar.getInstance();
		Calendar locCalIntDateOrigin = null;

		locCalIntDate.setTime(interestDate);
		locCalBuyDate.setTime(buyDate);
		locCalDueDate.setTime(dueDate);

		// set initial interest date after buy date
		locCalIntDate.set(Calendar.YEAR, locCalBuyDate.get(Calendar.YEAR));

		if (locCalIntDate.before(locCalBuyDate)) {
			locCalIntDate.add(Calendar.YEAR, 1);
		}

		locCalIntDateOrigin = (Calendar) locCalIntDate.clone();
		locInterestDates.add(locCalIntDate.getTime());

		// get next interest dates after initial interest date
		while (locCalIntDate.before(locCalDueDate)) {
			locCalIntDate.add(Calendar.MONTH, locMonthIntervall);
			locInterestDates.add(locCalIntDate.getTime());
		}

		// get next interest dates before initial interest date
		while (locCalIntDateOrigin.after(locCalBuyDate)) {
			locCalIntDateOrigin.add(Calendar.MONTH, -locMonthIntervall);

			if (locCalIntDateOrigin.after(locCalBuyDate)) {
				locInterestDates.add(locCalIntDateOrigin.getTime());
			}

		}

		// sort items ascending
		Collections.sort(locInterestDates);

		return locInterestDates;
	}
	
	private int calMonthsIntervall(byte intervall) {
		return MONTH_OF_YEAR / intervall;
	}
	
	private int calDaysPerYear(Date date) {
		Calendar locCal = Calendar.getInstance();
		locCal.setTime(date);

		return locCal.get(Calendar.DAY_OF_YEAR);
	}
	
	private double calReturnPerYearWithIntervall(double nominalValue, double interestPerYear, byte interestIntervall,
			int daysPerYear) {
		double locReturn;

		locReturn = ((nominalValue * interestPerYear) / 100) / interestIntervall;

		return locReturn;
	}
	
	private TimelineBondHeader calStartEndDate(TimelineBondHeader bondHeaderResult) 
	{
			Calendar locCalStartDate = null;
			Calendar locCalEndDate = null;
			
			ArrayList<TimelineBondInterest> locBondTotalInterestResultList = bondHeaderResult.getBondTotalInterestResult();

			for (TimelineBondInterest bondInterestResult : locBondTotalInterestResultList) {

				Calendar locCal = Calendar.getInstance();
				locCal.setTime(bondInterestResult.getInterestDate());

				if (locCalStartDate == null) {
					locCalStartDate = Calendar.getInstance();
					locCalStartDate.setTime(locCal.getTime());

					locCalEndDate = Calendar.getInstance();
					locCalEndDate.setTime(locCal.getTime());
				} else if (locCal.before(locCalStartDate)) {
					locCalStartDate.setTime(locCal.getTime());
				} else if (locCal.after(locCalEndDate)) {
					locCalEndDate.setTime(locCal.getTime());
				}
			}

			bondHeaderResult.setStartDate(locCalStartDate.getTime());
			bondHeaderResult.setEndDate(locCalEndDate.getTime());
			
			return bondHeaderResult;
	}
	
	private Date getFirstBuyDate(BondHeader bondHeader) {
		ArrayList<BondItemBuy> locBondItemBuyList = (ArrayList<BondItemBuy>) bondHeader.getBondItemBuy();

		// sort items ascending by buy date
		Collections.sort(locBondItemBuyList, new ComparatorDateForBondItemBuy());

		// return first buy date
		return locBondItemBuyList.get(0).getBuyDate();
	}
}
