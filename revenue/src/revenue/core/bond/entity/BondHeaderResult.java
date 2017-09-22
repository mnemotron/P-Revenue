package revenue.core.bond.entity;

import java.util.ArrayList;
import revenue.entity.BondHeader;

public class BondHeaderResult
{
	private BondHeader bondHeader;
	private ArrayList<BondItemResult> bondItemsResult;

	public BondHeader getBondHeader()
	{
		return bondHeader;
	}

	public void setBondHeader(BondHeader bondHeader)
	{
		this.bondHeader = bondHeader;
	}

	public ArrayList<BondItemResult> getBondItemResult()
	{
		return bondItemsResult;
	}

	public void setBondItemResult(ArrayList<BondItemResult> bondItemResult)
	{
		this.bondItemsResult = bondItemResult;
	}
}
