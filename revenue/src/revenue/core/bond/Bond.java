package revenue.core.bond;

import java.util.Collections;
import java.util.Date;

import revenue.core.util.DateComparator;
import revenue.entities.BondHeader;

public class Bond
{

	public static final byte InterestIntervall_Yearly = 1;
	public static final byte InterestIntervall_HalfYearly = 2;
	public static final byte InterestIntervall_Quarterly = 4;
	public static final byte InterestIntervall_Monthly = 12;

	private BondHeader Bond;

	public Bond()
	{
	}

	public Bond(BondHeader bond)
	{
		Bond = bond;
	}

	public void calReturnTimeline()
	{

		//get runtime dates (first buy date, due date)
		Date locFirstBuyDate = getFirstBuyDate();
		

		// 2. Rendite pro Tag

		// 3. Rendite pro Monat/Quartal/Jahr

	}

	private Date getDueDate()
	{
		return Bond.getDueDate();
	}

	private Date getFirstBuyDate()
	{

		// sort items ascending by buy date
		Collections.sort(Bond.getBondItemsBuy(), new DateComparator());

		// return first buy date

		return new Date();
	}

	private void calReturnPerDay()
	{

	}

}
