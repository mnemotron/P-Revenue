package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResBond
{
	private long bondId;

	private ArrayList<ResBondItemBuy> bondItemBuyList = new ArrayList<ResBondItemBuy>();
	
	private ArrayList<ResBondItemInterestRevenue> bondTotalInterestResult = new ArrayList<ResBondItemInterestRevenue>();

	public ResBond()
	{

	}

	public long getBondId()
	{
		return bondId;
	}

	public void setBondId(long bondId)
	{
		this.bondId = bondId;
	}

	public ArrayList<ResBondItemBuy> getBondItemBuyList()
	{
		return bondItemBuyList;
	}

	public void setBondItemBuyList(ArrayList<ResBondItemBuy> bondItemBuyList)
	{
		this.bondItemBuyList = bondItemBuyList;
	}

	public ArrayList<ResBondItemInterestRevenue> getBondTotalInterestResult() {
		return bondTotalInterestResult;
	}

	public void setBondTotalInterestResult(ArrayList<ResBondItemInterestRevenue> bondTotalInterestResult) {
		this.bondTotalInterestResult = bondTotalInterestResult;
	}
}
