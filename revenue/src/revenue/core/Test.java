package revenue.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import revenue.core.bond.Bond;
import revenue.entities.BondHeader;
import revenue.entities.BondItemBuy;

public class Test {

	public static void main(String[] args) {
	
		BondItemBuy bi = new BondItemBuy(2000, new Date(2017, 02, 01), 100, null, null);
		
		ArrayList<BondItemBuy> bondItemsBuy = new ArrayList<BondItemBuy>();
		bondItemsBuy.add(bi);
		
		BondHeader bh = new BondHeader("Test", "TEST", "TEST", "TEST", 6.0, BondHeader.InterestIntervallYearly, new Date(2017, 1, 1), new Date(2020, 5, 1), bondItemsBuy);
	
		
		Bond bond = new Bond(bh);
		
//		bond.calcReturnPerYearWithIntervall(2000, 6.0, BondHeader.InterestIntervallYearly, 360);
		
		ArrayList<Date> locDate = bond.getNextInterestDates(new Date(116, 4, 1), new Date(116, 0, 1), new Date(120, 0, 1), BondHeader.InterestIntervallQuarterly);

		
		for (Date date : locDate) {
			
	         DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
             
             //to convert Date to String, use format method of SimpleDateFormat class.
             String strDate = dateFormat.format(date);

			
			System.out.println(strDate);
			
		}
		
	}

}
