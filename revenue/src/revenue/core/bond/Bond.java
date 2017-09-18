package revenue.core.bond;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import revenue.core.util.DateComparator;
import revenue.entities.BondHeader;

public class Bond {

	// TODO: Stückzinsen: Stückzins = Nominalwert × Zinssatz × Tage/360
	private BondHeader bond;

	public Bond() {
	}

	public Bond(BondHeader bond) {
		this.bond = bond;
	}

	public void calReturnTimeline() {

		// get runtime dates (first buy date, due date)
		// Date locFirstBuyDate = getFirstBuyDate();

		// 2. Rendite pro Tag

		// 3. Rendite pro Monat/Quartal/Jahr

	}

	private Date getDueDate() {
		return bond.getDueDate();
	}

	public Date getFirstBuyDate() {
		// sort items ascending by buy date
		Collections.sort(bond.getBondItemsBuy(), new DateComparator());

		// return first buy date
		return bond.getBondItemsBuy().get(0).getBuyDate();
	}

	private double calcReturnPerYearWithIntervall(double nominalValue, double interestPerYear, byte interestIntervall,
			int daysPerYear) {
		double locReturn;

		locReturn = ((nominalValue * interestPerYear) / 100) / interestIntervall;

		return locReturn;
	}

	private int calMonthsIntervall(byte intervall) {
		return 12 / intervall;
	}

	private ArrayList<Date> getNextInterestDates(Date buyDate, Date interestDate, Date dueDate,
			byte interestIntervall) {

		ArrayList<Date> locInterestDates = new ArrayList<Date>();

		Calendar locCalIntDate = Calendar.getInstance();

		int locMonthIntervall = calMonthsIntervall(interestIntervall);

		locCalIntDate.setTime(interestDate);

		locCalIntDate.add(Calendar.MONTH, locMonthIntervall);

		Calendar locCalBuyDate = Calendar.getInstance();
		// Calendar locCalIntDate = Calendar.getInstance();
		//
		locCalBuyDate.setTime(buyDate);
		// locCalIntDate.setTime(interestDate);
		//
		int locBuyDateMonth = locCalBuyDate.get(Calendar.DAY_OF_MONTH);
		int locIntDateMonth = locCalIntDate.get(Calendar.DAY_OF_MONTH);

		// Intervall 2 -> interestDate 12Monate / 2 = 6 Monate
		// InterestDate+/-6Monate

		if (locBuyDateMonth == locIntDateMonth) {

		} else if (locBuyDateMonth < locIntDateMonth) {
			
			

		} else if (locBuyDateMonth > locIntDateMonth) {

		}

		// int locDateOrder = buyDate.compareTo(interestDate);
		//
		// if (locDateOrder == 0) // equal
		// {
		//
		// } else if (locDateOrder < 0) // buy date before interest date
		// {
		//
		// } else if (locDateOrder > 0) // buy date after interest date
		// {
		//
		// }

		return locInterestDates;
	}

	private Date getPreviousInterestDate() {
		Date locFirstBuyDate = getFirstBuyDate();
		//
		// bond.getInterestDate()
		// bond.getInterestIntervall()

		// interestdate -> duedate

		return new Date();

	}

	private void calReturnPerDay() {

	}

}
