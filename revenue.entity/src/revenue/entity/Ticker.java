package revenue.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import revenue.entity.ShareHeader;
import javax.persistence.ManyToOne;
import revenue.entity.Portfolio;
import javax.persistence.OneToOne;
import revenue.entity.Depot;
import revenue.entity.StockDataProvider;

@Entity(name = "Ticker")
@Table(name = "T_TICKER")
public class Ticker implements Serializable
{

	private static final long serialVersionUID = 1L;

	public Ticker()
	{
	}

	@Id
	@GeneratedValue
	private long id;
	
	private String tickerId;

	@ManyToOne
	private ShareHeader shareHeader;

	@OneToOne
	private Portfolio portfolio;

	@OneToOne
	private Depot depot;

	@ManyToOne
	private StockDataProvider stockDataProvider;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTickerId()
	{
		return tickerId;
	}

	public void setTickerId(String param)
	{
		this.tickerId = param;
	}

	public ShareHeader getShareHeader() {
	    return shareHeader;
	}

	public void setShareHeader(ShareHeader param) {
	    this.shareHeader = param;
	}

	public Portfolio getPortfolio() {
	    return portfolio;
	}

	public void setPortfolio(Portfolio param) {
	    this.portfolio = param;
	}

	public Depot getDepot() {
	    return depot;
	}

	public void setDepot(Depot param) {
	    this.depot = param;
	}

	public StockDataProvider getStockDataProvider() {
	    return stockDataProvider;
	}

	public void setStockDataProvider(StockDataProvider param) {
	    this.stockDataProvider = param;
	}

}