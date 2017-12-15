package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResDepot
{
	private long depotId;
	
	private String depotName;
	
	private ArrayList<ResBondHeader> bondList = new ArrayList<ResBondHeader>();
	
	private String startDate;
	
	private String endDate;

	public ResDepot()
	{

	}

	public long getDepotId()
	{
		return depotId;
	}

	public void setDepotId(long depotId)
	{
		this.depotId = depotId;
	}

	public ArrayList<ResBondHeader> getBondList()
	{
		return bondList;
	}

	public void setBondList(ArrayList<ResBondHeader> bondList)
	{
		this.bondList = bondList;
	}

	public String getDepotName()
	{
		return depotName;
	}

	public void setDepotName(String depotName)
	{
		this.depotName = depotName;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
}
