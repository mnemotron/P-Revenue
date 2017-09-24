package revenue.core.bond;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import revenue.core.bond.entity.BondHeaderResult;
import revenue.core.bond.entity.BondInterestResult;
import revenue.core.bond.entity.BondItemResult;
import revenue.core.util.ComparatorDate;
import revenue.entity.BondHeader;
import revenue.entity.BondItemBuy;

public class Bond
{

	// TODO: Stückzinsen: Stückzins = Nominalwert × Zinssatz × Tage/360
	private static final int MONTH_OF_YEAR = 12;

	private ArrayList<BondHeaderResult> bonds;

	public Bond()
	{
		this.bonds = new ArrayList<BondHeaderResult>();
	}

	public void addBond(BondHeader bond)
	{
		BondHeaderResult locBondHeaderResult = new BondHeaderResult();

		// add header to result
		locBondHeaderResult.setBondHeader(bond);

		// add bond buy items to result
		this.addBondItemsToResult(locBondHeaderResult, bond.getBondItemsBuy());
	}

	public void calReturnTimeline()
	{
		for (BondHeaderResult locBond : this.bonds)
		{
			ArrayList<BondItemResult> locBondItemsResult = locBond.getBondItemResult();

			double locInterestPerYear = locBond.getBondHeader().getInterestPerYear();

			// get due date
			Date locDueDate = this.getDueDate(locBond.getBondHeader());

			// get interest date
			Date locInterestDate = locBond.getBondHeader().getInterestDate();

			// get interest intervall
			byte locInterestIntervall = locBond.getBondHeader().getInterestIntervall();

			// calculate return for each bond item
			for (BondItemResult bondItemResult : locBondItemsResult)
			{

				ArrayList<BondInterestResult> locBondInterestDates = new ArrayList<BondInterestResult>();

				Date locBuyDate = bondItemResult.getBondItemBuy().getBuyDate();

				// determine interest dates based on buy date
				ArrayList<Date> locNextInterestDates = this.getNextInterestDates(locBuyDate, locInterestDate, locDueDate, locInterestIntervall);

				for (Date interestDate : locNextInterestDates)
				{
					int locDaysPerYear = this.calcDaysPerYear(interestDate);

					double locReturnPerYearIntervall = this.calcReturnPerYearWithIntervall(bondItemResult.getBondItemBuy().getNominalValue(), locInterestPerYear, locInterestIntervall, locDaysPerYear);

					BondInterestResult locBondInterestDate = new BondInterestResult();

					locBondInterestDate.setInterestDate(interestDate);
					locBondInterestDate.setInterest(locReturnPerYearIntervall);

					locBondInterestDates.add(locBondInterestDate);

				}

				bondItemResult.setBondInterestDates(locBondInterestDates);
			}
		}
	}

	private void addBondItemsToResult(BondHeaderResult bondHeaderResult, ArrayList<BondItemBuy> bondItemsBuy)
	{
		ArrayList<BondItemResult> locBondItemsResult = new ArrayList<BondItemResult>();

		for (BondItemBuy bondItemBuy : bondItemsBuy)
		{
			BondItemResult locBondItemResult = new BondItemResult();
			locBondItemResult.setBondItemBuy(bondItemBuy);
			locBondItemsResult.add(locBondItemResult);
		}

		bondHeaderResult.setBondItemResult(locBondItemsResult);
	}

	private int calcDaysPerYear(Date date)
	{
		Calendar locCal = Calendar.getInstance();
		locCal.setTime(date);

		return locCal.get(Calendar.DAY_OF_YEAR);
	}

	private Date getDueDate(BondHeader bondHeader)
	{
		return bondHeader.getDueDate();
	}

	private Date getFirstBuyDate(BondHeader bondHeader)
	{
		// sort items ascending by buy date
		Collections.sort(bondHeader.getBondItemsBuy(), new ComparatorDate());

		// return first buy date
		return bondHeader.getBondItemsBuy().get(0).getBuyDate();
	}

	private double calcReturnPerYearWithIntervall(double nominalValue, double interestPerYear, byte interestIntervall, int daysPerYear)
	{
		double locReturn;

		locReturn = ((nominalValue * interestPerYear) / 100) / interestIntervall;

		return locReturn;
	}

	private int calcMonthsIntervall(byte intervall)
	{
		return MONTH_OF_YEAR / intervall;
	}

	public ArrayList<Date> getNextInterestDates(Date buyDate, Date interestDate, Date dueDate, byte interestIntervall)
	{

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

		if (locCalIntDate.before(locCalBuyDate))
		{
			locCalIntDate.add(Calendar.YEAR, 1);
		}

		locCalIntDateOrigin = (Calendar) locCalIntDate.clone();
		locInterestDates.add(locCalIntDate.getTime());

		// get next interest dates after initial interest date
		while (locCalIntDate.before(locCalDueDate))
		{
			locCalIntDate.add(Calendar.MONTH, locMonthIntervall);
			locInterestDates.add(locCalIntDate.getTime());
		}

		// get next interest dates before initial interest date
		while (locCalIntDateOrigin.after(locCalBuyDate))
		{
			locCalIntDateOrigin.add(Calendar.MONTH, -locMonthIntervall);

			if (locCalIntDateOrigin.after(locCalBuyDate))
			{
				locInterestDates.add(locCalIntDateOrigin.getTime());
			}

		}

		// sort items ascending by buy date
		Collections.sort(locInterestDates);

		return locInterestDates;
	}

}
