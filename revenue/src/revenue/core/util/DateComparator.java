package revenue.core.util;

import java.util.Comparator;
import revenue.entities.BondItemBuy;

public class DateComparator implements Comparator<BondItemBuy>
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
