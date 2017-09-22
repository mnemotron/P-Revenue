package revenue.entity;

import java.util.Currency;

public class Fee
{

	private String FeeDescription;
	private double Fee;
	private Currency FeeUnit;

	public Fee()
	{
		
	}

	public Fee(String feeDescription, double fee, Currency feeUnit)
	{
		FeeDescription = feeDescription;
		Fee = fee;
		FeeUnit = feeUnit;
	}

	public String getFeeDescription()
	{
		return FeeDescription;
	}

	public void setFeeDescription(String feeDescription)
	{
		FeeDescription = feeDescription;
	}

	public double getFee()
	{
		return Fee;
	}

	public void setFee(double fee)
	{
		Fee = fee;
	}

	public Currency getFeeUnit()
	{
		return FeeUnit;
	}

	public void setFeeUnit(Currency feeUnit)
	{
		FeeUnit = feeUnit;
	}

}
