package revenue.entities;

import java.util.ArrayList;
import java.util.Date;

public class BondHeader
{
	private String name;
	private String area;
	private String wkn;
	private String isin;
	private double interestPerYear; // %
	private byte interestIntervall;
	private Date interestDate;
	private Date dueDate;
	private ArrayList<BondItemBuy> bondItemsBuy;

	public BondHeader(String name, String area, String wkn, String isin, double interestPerYear, byte interestIntervall,
			Date interestDate, Date dueDate, ArrayList<BondItemBuy> bondItemsBuy)
	{
		this.name = name;
		this.area = area;
		this.wkn = wkn;
		this.isin = isin;
		this.interestPerYear = interestPerYear;
		this.interestIntervall = interestIntervall;
		this.interestDate = interestDate;
		this.dueDate = dueDate;
		this.bondItemsBuy = bondItemsBuy;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getWkn()
	{
		return wkn;
	}

	public void setWkn(String wkn)
	{
		this.wkn = wkn;
	}

	public String getIsin()
	{
		return isin;
	}

	public void setIsin(String isin)
	{
		this.isin = isin;
	}

	public double getInterestPerYear()
	{
		return interestPerYear;
	}

	public void setInterestPerYear(double interestPerYear)
	{
		this.interestPerYear = interestPerYear;
	}

	public byte getInterestIntervall()
	{
		return interestIntervall;
	}

	public void setInterestIntervall(byte interestIntervall)
	{
		this.interestIntervall = interestIntervall;
	}

	public Date getInterestDate()
	{
		return interestDate;
	}

	public void setInterestDate(Date interestDate)
	{
		this.interestDate = interestDate;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}

	public ArrayList<BondItemBuy> getBondItemsBuy()
	{
		return bondItemsBuy;
	}

	public void setBondItemsBuy(ArrayList<BondItemBuy> bondItemsBuy)
	{
		this.bondItemsBuy = bondItemsBuy;
	}
}
