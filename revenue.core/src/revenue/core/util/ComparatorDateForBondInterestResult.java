package revenue.core.util;

import java.util.Comparator;

import revenue.core.bond.entity.BondInterestResult;

public class ComparatorDateForBondInterestResult implements Comparator<BondInterestResult>
{

	@Override
	public int compare(BondInterestResult o1, BondInterestResult o2)
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
