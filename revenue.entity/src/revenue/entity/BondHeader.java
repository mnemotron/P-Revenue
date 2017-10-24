package revenue.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import revenue.entity.Interest;

@Entity(name = "BondHeader")
@Table(name = "T_BONDHEADER")
@XmlRootElement
public class BondHeader implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final byte INTEREST_INTERVALL_YEARLY = 1;
	public static final byte INTEREST_INTERVALL_HALF_YEARLY = 2;
	public static final byte INTEREST_INTERVALL_QUARTERLY = 4;
	public static final byte INTEREST_INTERVALL_MONTHLY = 12;

	public BondHeader() {
	}

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String isin;
	
	private String wkn;
	
	private String area;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
	
	private byte interestIntervall;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date interestDate;

	@OneToMany(mappedBy = "bondHeader")
	private Collection<BondItemBuy> bondItemBuy = new ArrayList<BondItemBuy>();

	@ManyToOne
	private Depot depot;

	@OneToOne
	private Portfolio portfolio;

	@OneToMany(mappedBy = "bondHeader")
	private Collection<Interest> interest = new ArrayList<Interest>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<BondItemBuy> getBondItem() {
		return bondItemBuy;
	}

	public void setBondItem(Collection<BondItemBuy> param) {
		this.bondItemBuy = param;
	}

	public String getName() {
		return name;
	}

	public void setName(String param) {
		this.name = param;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String param) {
		this.isin = param;
	}

	public String getWkn() {
		return wkn;
	}

	public void setWkn(String param) {
		this.wkn = param;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String param) {
		this.area = param;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date param) {
		this.dueDate = param;
	}

	public byte getInterestIntervall() {
		return interestIntervall;
	}

	public void setInterestIntervall(byte param) {
		this.interestIntervall = param;
	}

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date param) {
		this.interestDate = param;
	}

	public Collection<BondItemBuy> getBondItemBuy() {
	    return bondItemBuy;
	}

	public void setBondItemBuy(Collection<BondItemBuy> param) {
	    this.bondItemBuy = param;
	}

	public Depot getDepot() {
	    return depot;
	}

	public void setDepot(Depot param) {
	    this.depot = param;
	}

	public Portfolio getPortfolio() {
	    return portfolio;
	}

	public void setPortfolio(Portfolio param) {
	    this.portfolio = param;
	}

	public Collection<Interest> getInterest() {
	    return interest;
	}

	public void setInterest(Collection<Interest> param) {
	    this.interest = param;
	}

}