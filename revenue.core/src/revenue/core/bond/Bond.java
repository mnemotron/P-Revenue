package revenue.core.bond;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import revenue.core.bond.entity.BondHeaderResult;
import revenue.core.bond.entity.BondInterestResult;
import revenue.core.bond.entity.BondItemResult;
import revenue.core.util.ComparatorDateForBondInterestResult;
import revenue.core.util.ComparatorDateForBondItemBuy;
import revenue.entity.BondHeader;
import revenue.entity.BondItemBuy;
import revenue.entity.Interest;

public class Bond
{

	// TODO: Stückzinsen: Stückzins = Nominalwert × Zinssatz × Tage/360
	private static final int MONTH_OF_YEAR = 12;

	private ArrayList<BondHeaderResult> bonds;

	public Bond()
	{
		this.bonds = new ArrayList<BondHeaderResult>();
	}

	public ArrayList<BondHeaderResult> getResult()
	{
		return bonds;
	}

	public void addBond(BondHeader bond)
	{
		BondHeaderResult locBondHeaderResult = new BondHeaderResult();

		// add header to result
		locBondHeaderResult.setBondHeader(bond);

		// add bond buy items to result
		ArrayList<BondItemBuy> locBondItemBuyList = new ArrayList<BondItemBuy>(bond.getBondItemBuy());

		this.addBondItemsToResult(locBondHeaderResult, locBondItemBuyList);

		this.bonds.add(locBondHeaderResult);
	}

	public void calReturnTimeline()
	{

		this.calInterestResult();

		this.calTotalInterestResult();
	}

	private void calInterestResult()
	{

		for (BondHeaderResult locBond : this.bonds)
		{
			ArrayList<BondItemResult> locBondItemsResult = locBond.getBondItemsResult();

			ArrayList<Interest> locInterestList = new ArrayList<Interest>(locBond.getBondHeader().getInterest());

			double locInterestPerYear = locInterestList.get(0).getInterest();

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

	private void calTotalInterestResult()
	{

		for (BondHeaderResult bond : this.bonds)
		{

			HashMap<String, BondInterestResult> locBondTotalInterestHash = new HashMap<String, BondInterestResult>();

			ArrayList<BondItemResult> locBondItemResultList = bond.getBondItemsResult();

			for (BondItemResult bondItemResult : locBondItemResultList)
			{

				ArrayList<BondInterestResult> locBondInterestResult = bondItemResult.getBondInterestDates();

				for (BondInterestResult bondInterestResult : locBondInterestResult)
				{

					if (locBondTotalInterestHash.containsKey(bondInterestResult.getInterestDate().toInstant().toString()))
					{
						// update value
						BondInterestResult locBondIR = locBondTotalInterestHash.get(bondInterestResult.getInterestDate().toInstant().toString());

						double locSum = bondInterestResult.getInterest() + locBondIR.getInterest();

						locBondIR.setInterest(locSum);

						locBondTotalInterestHash.put(bondInterestResult.getInterestDate().toInstant().toString(), locBondIR);
					}
					else
					{
						// insert new key and value
						BondInterestResult locValue = new BondInterestResult();
						locValue.setInterest(bondInterestResult.getInterest());
						locValue.setInterestDate(bondInterestResult.getInterestDate());

						locBondTotalInterestHash.put(bondInterestResult.getInterestDate().toInstant().toString(), locValue);
					}

				}

			}

			ArrayList<BondInterestResult> locBondTotalInterestList = new ArrayList<BondInterestResult>(locBondTotalInterestHash.values());

			// sort items ascending by interest date
			Collections.sort(locBondTotalInterestList, new ComparatorDateForBondInterestResult());
			
			bond.setBondTotalInterestResult(locBondTotalInterestList);
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
		ArrayList<BondItemBuy> locBondItemBuyList = (ArrayList<BondItemBuy>) bondHeader.getBondItemBuy();

		// sort items ascending by buy date
		Collections.sort(locBondItemBuyList, new ComparatorDateForBondItemBuy());

		// return first buy date
		return locBondItemBuyList.get(0).getBuyDate();
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

	private ArrayList<Date> getNextInterestDates(Date buyDate, Date interestDate, Date dueDate, byte interestIntervall)
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

		// sort items ascending
		Collections.sort(locInterestDates);

		return locInterestDates;
	}

}
