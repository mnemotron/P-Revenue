package revenue.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "AccountHeader")
@Table(name = "T_ACCOUNTHEADER")
public class AccountHeader implements Serializable
{

	private static final long serialVersionUID = 1L;

	public AccountHeader()
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