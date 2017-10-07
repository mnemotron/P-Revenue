package revenue.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

import revenue.entity.Depot;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

@Entity(name = "Portfolio")
@Table(name = "T_PORTFOLIO")
@XmlRootElement
public class Portfolio implements Serializable
{

	private static final long serialVersionUID = 1L;

	public Portfolio()
	{
	}

	@Id
	@GeneratedValue
	private long id;

	@Basic
	@Lob
	private String name;

	@Basic
	@Temporal(TIMESTAMP)
	private Date creationDate;

	@OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
	private Collection<Depot> depot = new ArrayList<Depot>();

	private String description;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String param)
	{
		this.name = param;
	}

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date param)
	{
		this.creationDate = param;
	}

	public Collection<Depot> getDepot()
	{
		return depot;
	}

	public void setDepot(Collection<Depot> param)
	{
		this.depot = param;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String param)
	{
		this.description = param;
	}

}