package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResDepot
{
	private long depotId;
	
	private String depotName;
	
	private ArrayList<ResBond> bondList = new ArrayList<ResBond>();

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

	public ArrayList<ResBond> getBondList()
	{
		return bondList;
	}

	public void setBondList(ArrayList<ResBond> bondList)
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
}
