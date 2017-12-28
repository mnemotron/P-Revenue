package revenue.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "AccountItemIncome")
@Table(name = "T_ACCOUNTITEMINCOME")
public class AccountItemIncome implements Serializable
{

	private static final long serialVersionUID = 1L;

	public AccountItemIncome()
	{
	}

	@Id
	@GeneratedValue
	private long id;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

}