package revenue.core.timeline.entity;

import java.util.ArrayList;

import revenue.entity.Portfolio;

public class TimelinePortfolio {

	private Portfolio portfolio;
	
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

}
