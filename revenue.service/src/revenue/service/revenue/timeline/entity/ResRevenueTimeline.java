package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResRevenueTimeline
{
	private long portfolioId;

	private ArrayList<ResDepot> depotList = new ArrayList<ResDepot>();

	public ResRevenueTimeline()
	{

	}

	public long getPortfolioId()
	{
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId)
	{
		this.portfolioId = portfolioId;
	}

	public ArrayList<ResDepot> getDepotList()
	{
		return depotList;
	}

	public void setDepotList(ArrayList<ResDepot> depotList)
	{
		this.depotList = depotList;
	}

}
