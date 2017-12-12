package revenue.core.timeline.entity;

import java.util.ArrayList;

import revenue.entity.BondItemBuy;

public class TimelineBondItem {
	private BondItemBuy bondItemBuy;
	private ArrayList<TimelineBondInterest> bondInterestDates;

	public ArrayList<TimelineBondInterest> getBondInterestDates() {
		return bondInterestDates;
	}

	public void setBondInterestDates(ArrayList<TimelineBondInterest> bondInterestDates) {
		this.bondInterestDates = bondInterestDates;
	}

	public BondItemBuy getBondItemBuy() {
		return bondItemBuy;
	}

	public void setBondItemBuy(BondItemBuy bondItemBuy) {
		this.bondItemBuy = bondItemBuy;
	}

}
