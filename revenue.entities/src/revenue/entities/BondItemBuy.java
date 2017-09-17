package revenue.entities;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;

public class BondItemBuy
{

	private double nominalValue;
	private Date buyDate;
	private double buyPercent;
	private ArrayList<Fee> buyFees;
	private ArrayList<BondSubItemSell> bondSubItemsSell;

	public double getNominalValue()
	{
		return nominalValue;
	}

	public void setNominalValue(double nominalValue)
	{
		this.nominalValue = nominalValue;
	}

	public Date getBuyDate()
	{
		return buyDate;
	}

	public void setBuyDate(Date buyDate)
	{
		this.buyDate = buyDate;
	}

	public double getBuyPrice()
	{
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice)
	{
		this.buyPrice = buyPrice;
	}

	public Currency getBuyPriceUnit()
	{
		return buyPriceUnit;
	}

	public void setBuyPriceUnit(Currency buyPriceUnit)
	{
		this.buyPriceUnit = buyPriceUnit;
	}

	public ArrayList<Fee> getBuyFees()
	{
		return buyFees;
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
