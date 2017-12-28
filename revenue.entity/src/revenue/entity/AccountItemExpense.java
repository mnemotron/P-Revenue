package revenue.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import revenue.entity.AccountHeader;
import revenue.entity.Portfolio;

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
	
	@ManyToOne
	private AccountHeader accountHeader;
	
	private String reasonForTransfer;
	
	private double value;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;

	@OneToOne
	private Portfolio portfolio;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public AccountHeader getAccountHeader()
	{
		return accountHeader;
	}

	public void setAccountHeader(AccountHeader param)
	{
		this.accountHeader = param;
	}

	public String getReasonForTransfer()
	{
		return reasonForTransfer;
	}

	public void setReasonForTransfer(String param)
	{
		this.reasonForTransfer = param;
	}

	public Date getTransactionDate()
	{
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate)
	{
		this.transactionDate = transactionDate;
	}

	public double getValue()
	{
		return value;
	}

	public void setValue(double value)
	{
		this.value = value;
	}

	public Portfolio getPortfolio() {
	    return portfolio;
	}

	public void setPortfolio(Portfolio param) {
	    this.portfolio = param;
	}

}