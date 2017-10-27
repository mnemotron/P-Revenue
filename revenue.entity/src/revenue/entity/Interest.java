package revenue.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import revenue.entity.BondHeader;
import javax.persistence.ManyToOne;

@Entity(name = "Interest")
@Table(name = "T_INTEREST")
public class Interest implements Serializable {

	private static final long serialVersionUID = 1L;

	public Interest() {
	}

	@Id
	@GeneratedValue
	private long id;

	private double interest;

	@Basic
	@Temporal(TIMESTAMP)
	private Date validFrom;

	@Basic
	@Temporal(TIMESTAMP)
	private Date validTo;

	@OneToOne
	private Portfolio portfolio;

	@OneToOne
	private Depot depot;

	@ManyToOne
	private BondHeader bondHeader;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date param) {
		this.validFrom = param;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date param) {
		this.validTo = param;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double param) {
		this.interest = param;
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

	public BondHeader getBondHeader() {
	    return bondHeader;
	}

	public void setBondHeader(BondHeader param) {
	    this.bondHeader = param;
	}

}