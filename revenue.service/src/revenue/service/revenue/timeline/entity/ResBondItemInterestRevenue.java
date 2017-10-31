package revenue.service.revenue.timeline.entity;

public class ResBondItemInterestRevenue
{
	private String interestDate;
	
	private double interestRevenue;
	
	public ResBondItemInterestRevenue()
	{
		
	}

	public String getInterestDate()
	{
		return interestDate;
	}

	public void setInterestDate(String interestDate)
	{
		this.interestDate = interestDate;
	}

	public double getInterestRevenue()
	{
		return interestRevenue;
	}

	public void setInterestRevenue(double interestRevenue)
	{
		this.interestRevenue = interestRevenue;
	}
}
