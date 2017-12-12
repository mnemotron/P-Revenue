package revenue.core.timeline.util;

import java.util.Comparator;

import revenue.core.timeline.entity.TimelineBondInterest;

public class ComparatorDateForBondInterestResult implements Comparator<TimelineBondInterest>
{

	@Override
	public int compare(TimelineBondInterest o1, TimelineBondInterest o2)
	{
		if (o1.getInterestDate() == null || o2.getInterestDate() == null)
		{
			return 0;
		}
		else
		{
			return o1.getInterestDate().compareTo(o2.getInterestDate());
		}
	}

}
