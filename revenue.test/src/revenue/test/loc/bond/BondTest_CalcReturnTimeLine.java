package revenue.test.loc.bond;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class BondTest_CalcReturnTimeLine
{

	public static void main(String[] args)
	{
		
//		TEST-TIMEZONE
		
		Calendar locC = Calendar.getInstance();	
		
		System.out.println(locC.getTime().toString());
		
		System.out.println(locC.getTime().toInstant());
		// ITEMS
		// nominal value: 2000, buy date: 01.02.2017, buy value: 100%
//		BondItemBuy locBondItem1 = new BondItemBuy(2000, new Date(117, 1, 1), 100, null, null);
//		
//		ArrayList<BondItemBuy> locBondItemsBuy = new ArrayList<BondItemBuy>();
//		locBondItemsBuy.add(locBondItem1);
//		
//		// HEADER
//		// interest date: 21.05., due date: 21.05.2020
//		BondHeader locBondHeader = new BondHeader("JUNG, DMS ANL.15/20", "Insurance", "A14J9D", "DE000A14J9D9", 6.0, BondHeader.INTEREST_INTERVALL_YEARLY, new Date(117, 4, 21), new Date(120, 4, 21), locBondItemsBuy);
//
//		Bond bond = new Bond(locBondHeader);
//
//		bond.calReturnTimeline();
	}

}
