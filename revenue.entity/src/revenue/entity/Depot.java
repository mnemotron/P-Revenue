package revenue.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import revenue.entity.BondHeader;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.OneToMany;

@Entity(name = "Depot")
@Table(name = "T_DEPOT")
public class Depot implements Serializable {

	private static final long serialVersionUID = 1L;

	public Depot() {
	}

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToOne
	private Portfolio portfolio;

	@OneToMany(mappedBy = "depot", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<BondHeader> bondHeader = new ArrayList<BondHeader>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String param) {
		this.name = param;
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

}