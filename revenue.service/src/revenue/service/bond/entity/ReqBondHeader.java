package revenue.service.bond.entity;

import java.util.Date;

public class ReqBondHeader
{
	private long id;

	private long depotId;

	private long portfolioId;

	private String name;

	private String isin;

	private String wkn;

	private String area;

	private Date dueDate;

	private byte interestIntervall;

	private Date interestDate;

	private double interest;

	public ReqBondHeader()
	{

	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getDepotId()
	{
		return depotId;
	}

	public void setDepotId(long depotId)
	{
		this.depotId = depotId;
	}

	public long getPortfolioId()
	{
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId)
	{
		this.portfolioId = portfolioId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getIsin()
	{
		return isin;
	}

	public void setIsin(String isin)
	{
		this.isin = isin;
	}

	public String getWkn()
	{
		return wkn;
	}

	public void setWkn(String wkn)
	{
		this.wkn = wkn;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public Date getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
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

	public double getInterest()
	{
		return interest;
	}

	public void setInterest(double interest)
	{
		this.interest = interest;
	}

}
