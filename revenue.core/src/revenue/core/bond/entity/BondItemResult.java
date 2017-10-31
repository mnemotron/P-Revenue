package revenue.core.bond.entity;

import java.util.ArrayList;

import revenue.entity.BondItemBuy;

public class BondItemResult
{
	private BondItemBuy bondItemBuy;
	private ArrayList<BondInterestResult> bondInterestDates;

	public ArrayList<BondInterestResult> getBondInterestDates()
	{
		return bondInterestDates;
	}

	public void setBondInterestDates(ArrayList<BondInterestResult> bondInterestDates)
	{
		this.bondInterestDates = bondInterestDates;
	}

	public BondItemBuy getBondItemBuy()
	{
		return bondItemBuy;
	}

	public void setBondItemBuy(BondItemBuy bondItemBuy)
	{
		this.bondItemBuy = bondItemBuy;
	}

}
