package mx.org.inegi.geo.map.xml.model;

import mx.org.inegi.geo.map.sql.util.NumberValidation;

/**
 * Depending on a given resolution, it will identify distinct elements.
 * 
 * @author yan.luevano
 * @author femat
 * 
 */
public class Resolution {

	private Double min;
	private Double max;

	public Resolution(Double min, Double max) {
		super();
		this.min = min;
		this.max = max;
	}

	public Resolution(String min, String max) {
		this.min = validDouble(min);
		this.max = validDouble(max);
	}

	private Double validDouble(String value) {
		if (value != null && value.trim().length() > 0)
			if (NumberValidation.isFloat(value))
				return Double.parseDouble(value);
		return null;
	}

	public boolean isWithinBounds(Double resolution) {
		if (min != null && max != null)
			if (resolution >= this.min && resolution <= this.max)
				return true;
		return false;
	}

	public Double getMin() {
		return this.min;
	}

	public Double getMax() {
		return this.max;
	}

}
