package revenue.core.bond;

import java.util.Collections;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Years;

import revenue.core.util.DateComparator;
import revenue.entities.BondHeader;

public class Bond
{

	private BondHeader bond;

	public Bond()
	{
	}

	public Bond(BondHeader bond)
	{
		this.bond = bond;
	}

	public void calReturnTimeline()
	{

		// get runtime dates (first buy date, due date)
		// Date locFirstBuyDate = getFirstBuyDate();

		// 2. Rendite pro Tag

		// 3. Rendite pro Monat/Quartal/Jahr

	}

	private DateTime getDueDate()
	{
		return bond.getDueDate();
	}

	public DateTime getFirstBuyDate()
	{
		// sort items ascending by buy date
		Collections.sort(bond.getBondItemsBuy(), new DateComparator());

		// return first buy date
		return bond.getBondItemsBuy().get(0).getBuyDate();
	}

	private DateTime getPreviousInterestDate()
	{
		DateTime locFirstBuyDate = getFirstBuyDate();
//		
//		bond.getInterestDate()
//		bond.getInterestIntervall()
		
		return new DateTime();
		
	}

	private void calReturnPerDay()
	{

	}

}
