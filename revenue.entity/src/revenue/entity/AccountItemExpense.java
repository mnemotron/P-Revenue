package revenue.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "AccountItemExpense")
@Table(name = "T_ACCOUNTITEMEXPENSE")
public class AccountItemExpense implements Serializable
{

	private static final long serialVersionUID = 1L;

	public AccountItemExpense()
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