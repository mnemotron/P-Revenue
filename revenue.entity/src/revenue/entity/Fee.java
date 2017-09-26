package revenue.entity;

import java.io.Serializable;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Fee")
@Table(name = "T_FEE")
public class Fee implements Serializable {

	private static final long serialVersionUID = 1L;

	public Fee() {
	}

	@Id
	@GeneratedValue
	private long id;
	
	private String description;
	
	private Currency unit;
	
	private double fee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String param) {
		this.description = param;
	}

	public Currency getUnit() {
		return unit;
	}

	public void setUnit(Currency param) {
		this.unit = param;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double param) {
		this.fee = param;
	}

}