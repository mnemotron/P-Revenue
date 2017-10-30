package revenue.service.bond.entity;

import java.util.Date;

public class ResBondItemBuy
{

	private long id;

	private long portfolioId;

	private long depotId;

	private long bondId;

	private String buyDate;

	private double buyPercent;

	private double nominalValue;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getPortfolioId()
	{
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId)
	{
		this.portfolioId = portfolioId;
	}

	public long getDepotId()
	{
		return depotId;
	}

	public void setDepotId(long depotId)
	{
		this.depotId = depotId;
	}

	public long getBondId()
	{
		return bondId;
	}

	public void setBondId(long bondId)
	{
		this.bondId = bondId;
	}

	public String getBuyDate()
	{
		return buyDate;
	}

	public void setBuyDate(String buyDate)
	{
		this.buyDate = buyDate;
	}

	public double getBuyPercent()
	{
		return buyPercent;
	}

	public void setBuyPercent(double buyPercent)
	{
		this.buyPercent = buyPercent;
	}

	public double getNominalValue()
	{
		return nominalValue;
	}

	public void setNominalValue(double nominalValue)
	{
		this.nominalValue = nominalValue;
	}

	public ResBondItemBuy()
	{

	}
}
