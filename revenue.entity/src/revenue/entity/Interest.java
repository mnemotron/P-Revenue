package revenue.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

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

}