package revenue.entity;

import java.io.Serializable;
import javax.persistence.*;
import revenue.entity.Depot;
import revenue.entity.BondItemBuy;
import java.util.Collection;

@Entity
@Table(name = "BondHeader")
public class BondHeader implements Serializable {

	private static final long serialVersionUID = 1L;

	public BondHeader() {
	}

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Depot depot;
	@OneToMany(mappedBy = "bondHeader")
	private Collection<BondItemBuy> bondItemBuy;
	private String name;
	private String isin;
	private String wkn;
	private String area;
	private String dueDate;
	private String interestIntervall;
	private String interestDate;
	private String interestPerYear;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot param) {
		this.depot = param;
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

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String param) {
		this.dueDate = param;
	}

	public String getInterestIntervall() {
		return interestIntervall;
	}

	public void setInterestIntervall(String param) {
		this.interestIntervall = param;
	}

	public String getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(String param) {
		this.interestDate = param;
	}

	public String getInterestPerYear() {
		return interestPerYear;
	}

	public void setInterestPerYear(String param) {
		this.interestPerYear = param;
	}

}