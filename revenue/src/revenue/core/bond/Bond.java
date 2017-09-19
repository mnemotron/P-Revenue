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

	public ArrayList<Date> getNextInterestDates(Date buyDate, Date interestDate, Date dueDate, byte interestIntervall) {

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
		
		// sort items ascending by buy date
		Collections.sort(locInterestDates);

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
