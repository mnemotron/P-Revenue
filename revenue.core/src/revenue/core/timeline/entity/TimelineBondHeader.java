package revenue.core.timeline.entity;

import java.util.ArrayList;
import java.util.Date;

import revenue.entity.BondHeader;

public class TimelineBondHeader {
	
	private BondHeader bondHeader;
	private ArrayList<TimelineBondItem> bondItemsResult;
	private ArrayList<TimelineBondInterest> bondTotalInterestResult;
	private Date startDate;
	private Date endDate;

	public BondHeader getBondHeader() {
		return bondHeader;
	}

	public void setBondHeader(BondHeader bondHeader) {
		this.bondHeader = bondHeader;
	}

	public ArrayList<TimelineBondItem> getBondItemsResult() {
		return bondItemsResult;
	}

	public ArrayList<TimelineBondInterest> getBondTotalInterestResult() {
		return bondTotalInterestResult;
	}

	public void setBondTotalInterestResult(ArrayList<TimelineBondInterest> bondTotalInterestResult) {
		this.bondTotalInterestResult = bondTotalInterestResult;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBondItemsResult(ArrayList<TimelineBondItem> bondItemsResult) {
		this.bondItemsResult = bondItemsResult;
	}
}
