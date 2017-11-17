package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResBond
{
	private long bondId;
	
	private String bondName;

	private ArrayList<ResBondItemBuy> bondItemBuyList = new ArrayList<ResBondItemBuy>();
	
	private ArrayList<ResBondItemInterestRevenue> bondTotalInterestResultList = new ArrayList<ResBondItemInterestRevenue>();

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

	public ArrayList<ResBondItemInterestRevenue> getBondTotalInterestResultList() {
		return bondTotalInterestResultList;
	}

	public void setBondTotalInterestResultList(ArrayList<ResBondItemInterestRevenue> bondTotalInterestResult) {
		this.bondTotalInterestResultList = bondTotalInterestResult;
	}

	public String getBondName()
	{
		return bondName;
	}

	public void setBondName(String bondName)
	{
		this.bondName = bondName;
	}
}
