package revenue.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Config")
@Table(name = "T_CONFIG")
public class Config implements Serializable {

	private static final long serialVersionUID = 1L;

	public Config() {
	}

	@Id
	private String key;

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String param) {
		this.value = param;
	}

}