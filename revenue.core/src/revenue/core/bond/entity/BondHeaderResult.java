package revenue.core.bond.entity;

import java.util.ArrayList;
import java.util.Date;

import revenue.entity.BondHeader;

public class BondHeaderResult
{
	private BondHeader bondHeader;
	private ArrayList<BondItemResult> bondItemsResult;
	private ArrayList<BondInterestResult> bondTotalInterestResult;
	private Date startDate;
	private Date endDate;

	public BondHeader getBondHeader()
	{
		return bondHeader;
	}

	public void setBondHeader(BondHeader bondHeader)
	{
		this.bondHeader = bondHeader;
	}

	public ArrayList<BondItemResult> getBondItemsResult()
	{
		return bondItemsResult;
	}

	public ArrayList<BondInterestResult> getBondTotalInterestResult()
	{
		return bondTotalInterestResult;
	}

	public void setBondTotalInterestResult(ArrayList<BondInterestResult> bondTotalInterestResult)
	{
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

	public void setBondItemsResult(ArrayList<BondItemResult> bondItemsResult) {
		this.bondItemsResult = bondItemsResult;
	}
}
