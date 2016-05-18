package mx.org.inegi.geo.map.domain;

import java.util.Collection;

import mx.org.inegi.geo.map.model.Field;

public class CensoLabel {

	private Collection<Field> geographical;

	private Collection<Field> economical;

	public Collection<Field> getGeographical() {
		return geographical;
	}

	public void setGeographical(Collection<Field> geographical) {
		this.geographical = geographical;
	}

	public Collection<Field> getEconomical() {
		return economical;
	}

	public void setEconomical(Collection<Field> economical) {
		this.economical = economical;
	}

}
