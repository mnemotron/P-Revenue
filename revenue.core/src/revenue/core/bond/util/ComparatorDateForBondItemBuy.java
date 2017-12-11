package revenue.core.bond.util;

import java.util.Comparator;

import revenue.entity.BondItemBuy;

public class ComparatorDateForBondItemBuy implements Comparator<BondItemBuy>
{

	@Override
	public int compare(BondItemBuy o1, BondItemBuy o2)
	{
		if (o1.getBuyDate() == null || o2.getBuyDate() == null)
		{
			return 0;
		}
		else
		{
			return o1.getBuyDate().compareTo(o2.getBuyDate());
		}
	}

}
