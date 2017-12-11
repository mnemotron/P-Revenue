package revenue.service.revenue.timeline.entity;

import java.util.ArrayList;

public class ResRevenueTimeline {
	private long portfolioId;

	private ArrayList<ResDepot> depotList = new ArrayList<ResDepot>();

	private String startDate;
	private String endDate;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
