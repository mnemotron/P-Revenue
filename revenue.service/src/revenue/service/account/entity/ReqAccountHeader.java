package revenue.service.account.entity;

public class ReqAccountHeader
{
	private long id;

	private long portfolioId;

	private String name;

	private String number;

	public ReqAccountHeader()
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

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

}
