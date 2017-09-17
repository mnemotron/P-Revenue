package revenue.entities;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class BondItemBuy
{

	private double nominalValue;
	private DateTime buyDate;
	private double buyPercent;
	private ArrayList<Fee> buyFees;
	private ArrayList<BondSubItemSell> bondSubItemsSell;

	public BondItemBuy(double nominalValue, DateTime buyDate, double buyPercent, ArrayList<Fee> buyFees,
			ArrayList<BondSubItemSell> bondSubItemsSell)
	{
		this.nominalValue = nominalValue;
		this.buyDate = buyDate;
		this.buyPercent = buyPercent;
		this.buyFees = buyFees;
		this.bondSubItemsSell = bondSubItemsSell;
	}

	public double getNominalValue()
	{
		return nominalValue;
	}

	public void setNominalValue(double nominalValue)
	{
		this.nominalValue = nominalValue;
	}

	public DateTime getBuyDate()
	{
		return buyDate;
	}

	public void setBuyDate(DateTime buyDate)
	{
		this.buyDate = buyDate;
	}

	public ArrayList<Fee> getBuyFees()
	{
		return buyFees;
	}

	public double getBuyPercent()
	{
		return buyPercent;
	}

	public void setBuyPercent(double buyPercent)
	{
		this.buyPercent = buyPercent;
	}

	public void setBuyFees(ArrayList<Fee> buyFees)
	{
		this.buyFees = buyFees;
	}

	public ArrayList<BondSubItemSell> getBondSubItemsSell()
	{
		return bondSubItemsSell;
	}

	public void setBondSubItemsSell(ArrayList<BondSubItemSell> bondSubItemsSell)
	{
		this.bondSubItemsSell = bondSubItemsSell;
	}
}
