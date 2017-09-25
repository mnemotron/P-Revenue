package revenue.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fee implements Serializable {

	private static final long serialVersionUID = 1L;

	public Fee() {
	}

	@Id
	@GeneratedValue
	private long id;
	private String description;
	private String unit;
	private String fee;

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String param) {
		this.unit = param;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String param) {
		this.fee = param;
	}

}