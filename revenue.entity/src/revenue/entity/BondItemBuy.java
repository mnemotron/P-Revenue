package revenue.entity;

import java.io.Serializable;
import revenue.entity.BondHeader;
import revenue.entity.Fee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "BondItemBuy")
@Table(name = "T_BONDITEMBUY")
public class BondItemBuy implements Serializable {

	private static final long serialVersionUID = 1L;

	public BondItemBuy() {
	}

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private BondHeader bondHeader;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private Date buyDate;
	
	private double buyPercent;
	
	private double nominalValue;
	
	@OneToMany
	private Collection<Fee> fee = new ArrayList<Fee>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BondHeader getBondHeader() {
		return bondHeader;
	}

	public void setBondHeader(BondHeader param) {
		this.bondHeader = param;
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

	public Collection<Fee> getFee() {
	    return fee;
	}

	public void setFee(Collection<Fee> param) {
	    this.fee = param;
	}

}