package revenue.service.depot.entity;

public class ResDepot
{
	private long id;

	private String name;
	
	private String number;

	private long portfolioId;

	public ResDepot()
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getPortfolioId()
	{
		return portfolioId;
	}

	public void setPortfolioId(long portfolioId)
	{
		this.portfolioId = portfolioId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
