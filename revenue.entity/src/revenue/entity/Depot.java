package revenue.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import revenue.entity.Portfolio;
import javax.persistence.ManyToOne;
import revenue.entity.BondHeader;
import java.util.Collection;
import javax.persistence.OneToMany;

@Entity
public class Depot implements Serializable {

	private static final long serialVersionUID = 1L;

	public Depot() {
	}

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Portfolio portfolio;

	@OneToMany(mappedBy = "depot")
	private Collection<BondHeader> bondHeader;

	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio param) {
		this.portfolio = param;
	}

	public Collection<BondHeader> getBondHeader() {
		return bondHeader;
	}

	public void setBondHeader(Collection<BondHeader> param) {
		this.bondHeader = param;
	}

	public String getName() {
		return name;
	}

	public void setName(String param) {
		this.name = param;
	}

}