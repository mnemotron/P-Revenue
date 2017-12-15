package revenue.core.timeline.entity;

import java.util.ArrayList;
import java.util.Date;

import revenue.entity.Portfolio;

public class TimelinePortfolio {

	private Portfolio portfolio;
	
	private Date startDate;
	private Date endDate;
	
	private ArrayList<TimelineDepot> bondDepotResult;

	public ArrayList<TimelineDepot> getBondDepotResult() {
		return bondDepotResult;
	}

	public void setBondDepotResult(ArrayList<TimelineDepot> bondDepotResult) {
		this.bondDepotResult = bondDepotResult;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

}
