package revenue.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import revenue.entity.Fee;
import java.util.Collection;
import javax.persistence.OneToMany;

@Entity(name = "BondItemBuy")
@Table(name = "T_BONDITEMBUY")
public class BondItemBuy implements Serializable {

	private static final long serialVersionUID = 1L;

	public BondItemBuy() {
	}

	@Id
	@GeneratedValue
	private long id;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date buyDate;
	
	private double buyPercent;
	
	private double nominalValue;
	
	@ManyToOne
	private BondHeader bondHeader;

	@OneToOne
	private Portfolio portfolio;

	@OneToOne
	private Depot depot;

	@OneToMany(mappedBy = "bondItemBuy")
	private Collection<Fee> fee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date param) {
		this.buyDate = param;
	}

	public double getBuyPercent() {
		return buyPercent;
	}

	public void setBuyPercent(double param) {
		this.buyPercent = param;
	}

	public double getNominalValue() {
		return nominalValue;
	}

	public void setNominalValue(double param) {
		this.nominalValue = param;
	}

	public BondHeader getBondHeader() {
	    return bondHeader;
	}

	public void setBondHeader(BondHeader param) {
	    this.bondHeader = param;
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

	public Collection<Fee> getFee() {
	    return fee;
	}

	public void setFee(Collection<Fee> param) {
	    this.fee = param;
	}

}