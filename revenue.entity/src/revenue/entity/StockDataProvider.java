package revenue.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="StockDataProvider")
@Table(name = "T_STOCKDATAPROVIDER")
public class StockDataProvider implements Serializable
{

	private static final long serialVersionUID = 1L;

	public StockDataProvider()
	{
	}

	@Id
	private String id;
	
	private String name;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String param)
	{
		this.name = param;
	}

}