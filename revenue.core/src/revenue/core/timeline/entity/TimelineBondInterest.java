package revenue.core.timeline.entity;

import java.util.Date;

public class TimelineBondInterest
{

	private Date interestDate;
	private double interest;

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
