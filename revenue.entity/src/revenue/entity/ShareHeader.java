package revenue.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import revenue.entity.Ticker;

@Entity(name = "ShareHeader")
@Table(name = "T_SHAREHEADER")
public class ShareHeader implements Serializable
{

	private static final long serialVersionUID = 1L;

	public ShareHeader()
	{
	}

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String isin;

	private String wkn;

	private String area;

	@ManyToOne
	private Depot depot;

	@OneToOne
	private Portfolio portfolio;

	@OneToMany(mappedBy = "shareHeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ShareItemBuy> shareItemBuy = new ArrayList<ShareItemBuy>();

	@OneToMany(mappedBy = "shareHeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<Ticker> ticker = new ArrayList<Ticker>();

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

	public String getIsin()
	{
		return isin;
	}

	public void setIsin(String isin)
	{
		this.isin = isin;
	}

	public String getWkn()
	{
		return wkn;
	}

	public void setWkn(String wkn)
	{
		this.wkn = wkn;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public Depot getDepot()
	{
		return depot;
	}

	public void setDepot(Depot param)
	{
		this.depot = param;
	}

	public Portfolio getPortfolio()
	{
		return portfolio;
	}

	public void setPortfolio(Portfolio param)
	{
		this.portfolio = param;
	}

	public Collection<ShareItemBuy> getShareItemBuy()
	{
		return shareItemBuy;
	}

	public void setShareItemBuy(Collection<ShareItemBuy> param)
	{
		this.shareItemBuy = param;
	}

	public Collection<Ticker> getTicker() {
	    return ticker;
	}

	public void setTicker(Collection<Ticker> param) {
	    this.ticker = param;
	}

}