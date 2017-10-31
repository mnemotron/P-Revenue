package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResBondItemBuy
{
	private long bondItemBuyId;

	private ArrayList<ResBondItemInterestRevenue> bondItemInterestRevenueList = new ArrayList<ResBondItemInterestRevenue>();

	public ResBondItemBuy()
	{

	}

	public long getBondItemBuyId()
	{
		return bondItemBuyId;
	}

	public void setBondItemBuyId(long bondItemBuyId)
	{
		this.bondItemBuyId = bondItemBuyId;
	}

	public ArrayList<ResBondItemInterestRevenue> getBondItemInterestRevenueList()
	{
		return bondItemInterestRevenueList;
	}

	public void setBondItemInterestRevenueList(ArrayList<ResBondItemInterestRevenue> bondItemInterestRevenueList)
	{
		this.bondItemInterestRevenueList = bondItemInterestRevenueList;
	}
}
