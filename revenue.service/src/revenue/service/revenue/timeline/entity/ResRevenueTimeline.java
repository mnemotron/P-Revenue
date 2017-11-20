package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResRevenueTimeline {
	private long portfolioId;

	private ArrayList<ResDepot> depotList = new ArrayList<ResDepot>();

	private int startYear;

	private int endYear;

	public ResRevenueTimeline() {

	}

	public long getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId) {
		this.portfolioId = portfolioId;
	}

	public ArrayList<ResDepot> getDepotList() {
		return depotList;
	}

	public void setDepotList(ArrayList<ResDepot> depotList) {
		this.depotList = depotList;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

}
