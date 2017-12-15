package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ReqRevenueTimeline
{
	private long portfolioId;
	
	private ArrayList<ReqDepot> depotList = new ArrayList<ReqDepot>();

	public ReqRevenueTimeline()
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

	public ArrayList<ReqDepot> getDepotList() {
		return depotList;
	}

	public void setDepotList(ArrayList<ReqDepot> depotList) {
		this.depotList = depotList;
	}
	
}
