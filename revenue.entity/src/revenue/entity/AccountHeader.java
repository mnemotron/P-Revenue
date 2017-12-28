package revenue.entity;

import java.io.Serializable;
import javax.persistence.*;
import revenue.entity.Portfolio;
import revenue.entity.AccountItemIncome;

import java.util.ArrayList;
import java.util.Collection;
import revenue.entity.AccountItemExpense;

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
	
	@ManyToOne
	private Portfolio portfolio;
	
	private String name;
	
	private String number;
	
	@OneToMany(mappedBy = "accountHeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<AccountItemIncome> accountItemIncome = new ArrayList<AccountItemIncome>();
	
	@OneToMany(mappedBy = "accountHeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<AccountItemExpense> accountItemExpense = new ArrayList<AccountItemExpense>();

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Portfolio getPortfolio()
	{
		return portfolio;
	}

	public void setPortfolio(Portfolio param)
	{
		this.portfolio = param;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String param)
	{
		this.name = param;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String param)
	{
		this.number = param;
	}

	public Collection<AccountItemIncome> getAccountItemIncome()
	{
		return accountItemIncome;
	}

	public void setAccountItemIncome(Collection<AccountItemIncome> param)
	{
		this.accountItemIncome = param;
	}

	public Collection<AccountItemExpense> getAccountItemExpense()
	{
		return accountItemExpense;
	}

	public void setAccountItemExpense(Collection<AccountItemExpense> param)
	{
		this.accountItemExpense = param;
	}

}