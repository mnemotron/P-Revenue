package revenue.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import revenue.entity.Portfolio;
import revenue.entity.Depot;
import revenue.entity.ShareHeader;

@Entity(name = "ShareItemBuy")
@Table(name = "T_SHAREITEMBUY")
public class ShareItemBuy implements Serializable
{

	private static final long serialVersionUID = 1L;

	public ShareItemBuy()
	{
	}

	@Id
	@GeneratedValue
	private long id;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date buyDate;

	@OneToOne
	private Portfolio portfolio;

	@OneToOne
	private Depot depot;

	@ManyToOne
	private ShareHeader shareHeader;

	private double quantity;

	private String buyRate;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Date getBuyDate()
	{
		return buyDate;
	}

	public void setBuyDate(Date buyDate)
	{
		this.buyDate = buyDate;
	}

	public Portfolio getPortfolio()
	{
		return portfolio;
	}

	public void setPortfolio(Portfolio param)
	{
		this.portfolio = param;
	}

	public Depot getDepot()
	{
		return depot;
	}

	public void setDepot(Depot param)
	{
		this.depot = param;
	}

	public ShareHeader getShareHeader()
	{
		return shareHeader;
	}

	public void setShareHeader(ShareHeader param)
	{
		this.shareHeader = param;
	}

	public double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(double quantity)
	{
		this.quantity = quantity;
	}

	public String getBuyRate()
	{
		return buyRate;
	}

	public void setBuyRate(String param)
	{
		this.buyRate = param;
	}

}