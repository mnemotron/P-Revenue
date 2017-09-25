package revenue.entity;

import java.io.Serializable;
import javax.persistence.*;
import revenue.entity.BondHeader;
import revenue.entity.Fee;
import java.util.Collection;

@Entity
@Table(name = "BondHeader")
public class BondItemBuy implements Serializable {

	private static final long serialVersionUID = 1L;

	public BondItemBuy() {
	}

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private BondHeader bondHeader;
	private String buyDate;
	private String buyPercent;
	private String nominalValue;
	@OneToMany
	private Collection<Fee> fee;

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

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String param) {
		this.buyDate = param;
	}

	public String getBuyPercent() {
		return buyPercent;
	}

	public void setBuyPercent(String param) {
		this.buyPercent = param;
	}

	public String getNominalValue() {
		return nominalValue;
	}

	public void setNominalValue(String param) {
		this.nominalValue = param;
	}

	public Collection<Fee> getFee() {
	    return fee;
	}

	public void setFee(Collection<Fee> param) {
	    this.fee = param;
	}

}