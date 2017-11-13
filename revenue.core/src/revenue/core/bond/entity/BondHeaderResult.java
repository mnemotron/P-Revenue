package revenue.core.bond.entity;

import java.util.ArrayList;
import revenue.entity.BondHeader;

public class BondHeaderResult
{
	private BondHeader bondHeader;
	private ArrayList<BondItemResult> bondItemsResult;
	private ArrayList<BondInterestResult> bondTotalInterestResult;

	public BondHeader getBondHeader()
	{
		return bondHeader;
	}

	public void setBondHeader(BondHeader bondHeader)
	{
		this.bondHeader = bondHeader;
	}

	public void setBondItemResult(ArrayList<BondItemResult> bondItemResult)
	{
		this.bondItemsResult = bondItemResult;
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
}
