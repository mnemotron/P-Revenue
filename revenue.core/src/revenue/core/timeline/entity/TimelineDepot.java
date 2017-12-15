package revenue.core.timeline.entity;

import java.util.ArrayList;
import java.util.Date;

import revenue.entity.Depot;

public class TimelineDepot {
	
	private Depot depot;
	
	private Date startDate;
	private Date endDate;

	private ArrayList<TimelineBondHeader> bondHeaderResult;

	public ArrayList<TimelineBondHeader> getBondHeaderResult() {
		return bondHeaderResult;
	}

	public void setBondHeaderResult(ArrayList<TimelineBondHeader> bondHeaderResult) {
		this.bondHeaderResult = bondHeaderResult;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
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
