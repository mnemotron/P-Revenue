package revenue.entities;

import java.util.ArrayList;
import java.util.Date;

public class BondSubItemSell
{

	private Date SellDate;
	private double sellPercent;
	private ArrayList<Fee> SellFees;

	public BondSubItemSell(Date sellDate, ArrayList<Fee> sellFees)
	{
		super();
		SellDate = sellDate;
		SellFees = sellFees;
	}

	public Date getSellDate()
	{
		return SellDate;
	}

	public void setSellDate(Date sellDate)
	{
		SellDate = sellDate;
	}

	public ArrayList<Fee> getSellFees()
	{
		return SellFees;
	}

	public void setSellFees(ArrayList<Fee> sellFees)
	{
		SellFees = sellFees;
	}

}
