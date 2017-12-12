package revenue.core.bond;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import revenue.core.bond.util.ComparatorDateForBondItemBuy;
import revenue.core.timeline.entity.TimelineBondHeader;
import revenue.core.timeline.entity.TimelineBondInterest;
import revenue.core.timeline.entity.TimelineBondItem;
import revenue.core.timeline.util.ComparatorDateForBondInterestResult;
import revenue.entity.BondHeader;
import revenue.entity.BondItemBuy;
import revenue.entity.Interest;

public class Bond {

	// TODO: Stückzinsen: Stückzins = Nominalwert × Zinssatz × Tage/360
	private static final int MONTH_OF_YEAR = 12;

	private TimelineBondHeader bond;

	public Bond() {
		this.bond = new TimelineBondHeader();
	}

	public void addBond(BondHeader bond) {
		TimelineBondHeader locBondHeaderResult = new TimelineBondHeader();

		// add header to result
		locBondHeaderResult.setBondHeader(bond);

		// add bond buy items to result
		ArrayList<BondItemBuy> locBondItemBuyList = new ArrayList<BondItemBuy>(bond.getBondItemBuy());

		this.addBondItemsToResult(locBondHeaderResult, locBondItemBuyList);

		this.bond = locBondHeaderResult;
	}

	public void calReturnTimeline() {
		this.calInterestResult();

		this.calTotalInterestResult();

		this.calStartEndDate();
	}

	private void calInterestResult() 
	{
		ArrayList<TimelineBondItem> locBondItemsResult = this.bond.getBondItemsResult();

		ArrayList<Interest> locInterestList = new ArrayList<Interest>(this.bond.getBondHeader().getInterest());

		double locInterestPerYear = locInterestList.get(0).getInterest();

		// get due date
		Date locDueDate = this.getDueDate(this.bond.getBondHeader());

		// get interest date
		Date locInterestDate = this.bond.getBondHeader().getInterestDate();

		// get interest intervall
		byte locInterestIntervall = this.bond.getBondHeader().getInterestIntervall();

		// calculate return for each bond item
		for (TimelineBondItem bondItemResult : locBondItemsResult) {

			ArrayList<TimelineBondInterest> locBondInterestDates = new ArrayList<TimelineBondInterest>();

			Date locBuyDate = bondItemResult.getBondItemBuy().getBuyDate();

			// determine interest dates based on buy date
			ArrayList<Date> locNextInterestDates = this.getNextInterestDates(locBuyDate, locInterestDate, locDueDate,
					locInterestIntervall);

			for (Date interestDate : locNextInterestDates) {
				int locDaysPerYear = this.calcDaysPerYear(interestDate);

				double locReturnPerYearIntervall = this.calcReturnPerYearWithIntervall(
						bondItemResult.getBondItemBuy().getNominalValue(), locInterestPerYear, locInterestIntervall,
						locDaysPerYear);

				TimelineBondInterest locBondInterestDate = new TimelineBondInterest();

				locBondInterestDate.setInterestDate(interestDate);
				locBondInterestDate.setInterest(locReturnPerYearIntervall);

				locBondInterestDates.add(locBondInterestDate);

			}

			bondItemResult.setBondInterestDates(locBondInterestDates);
		}

	}

	private void calTotalInterestResult() 
	{
			HashMap<String, TimelineBondInterest> locBondTotalInterestHash = new HashMap<String, TimelineBondInterest>();

			ArrayList<TimelineBondItem> locBondItemResultList = this.bond.getBondItemsResult();

			for (TimelineBondItem bondItemResult : locBondItemResultList) {

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

			this.bond.setBondTotalInterestResult(locBondTotalInterestList);
	}

	private void calStartEndDate() 
	{
			ArrayList<TimelineBondInterest> locBondTotalInterestResult = this.bond.getBondTotalInterestResult();

			Calendar locCalStartDate = null;
			Calendar locCalEndDate = null;

			for (TimelineBondInterest bondInterestResult : locBondTotalInterestResult) {

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

			this.bond.setStartDate(locCalStartDate.getTime());
			this.bond.setEndDate(locCalEndDate.getTime());
	}

	private void addBondItemsToResult(TimelineBondHeader bondHeaderResult, ArrayList<BondItemBuy> bondItemsBuy) {
		ArrayList<TimelineBondItem> locBondItemsResult = new ArrayList<TimelineBondItem>();

		for (BondItemBuy bondItemBuy : bondItemsBuy) {
			TimelineBondItem locBondItemResult = new TimelineBondItem();
			locBondItemResult.setBondItemBuy(bondItemBuy);
			locBondItemsResult.add(locBondItemResult);
		}

		bondHeaderResult.setBondItemsResult(locBondItemsResult);
	}

	private int calcDaysPerYear(Date date) {
		Calendar locCal = Calendar.getInstance();
		locCal.setTime(date);

		return locCal.get(Calendar.DAY_OF_YEAR);
	}

	private Date getDueDate(BondHeader bondHeader) {
		return bondHeader.getDueDate();
	}

	private Date getFirstBuyDate(BondHeader bondHeader) {
		ArrayList<BondItemBuy> locBondItemBuyList = (ArrayList<BondItemBuy>) bondHeader.getBondItemBuy();

		// sort items ascending by buy date
		Collections.sort(locBondItemBuyList, new ComparatorDateForBondItemBuy());

		// return first buy date
		return locBondItemBuyList.get(0).getBuyDate();
	}

	private double calcReturnPerYearWithIntervall(double nominalValue, double interestPerYear, byte interestIntervall,
			int daysPerYear) {
		double locReturn;

		locReturn = ((nominalValue * interestPerYear) / 100) / interestIntervall;

		return locReturn;
	}

	private int calcMonthsIntervall(byte intervall) {
		return MONTH_OF_YEAR / intervall;
	}

	private ArrayList<Date> getNextInterestDates(Date buyDate, Date interestDate, Date dueDate,
			byte interestIntervall) {

		ArrayList<Date> locInterestDates = new ArrayList<Date>();

		int locMonthIntervall = calcMonthsIntervall(interestIntervall);

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

	public TimelineBondHeader getBond() 
	{
		return this.bond;
	}

}
