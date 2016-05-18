package mx.org.inegi.geo.map.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class CensoLabelGraphicInfo {
	@Min(0)
	private int type;
	@NotEmpty
	private String ent;

	private String mun;

	private List<String> listCompare;
	@NotEmpty
	private String variable;

	// @Min(1)
	private int id;

	private int typeSector;

	private String year;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEnt() {
		return ent;
	}

	public void setEnt(String ent) {
		this.ent = ent;
	}

	public String getMun() {
		return mun;
	}

	public void setMun(String mun) {
		this.mun = mun;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isNational() {
		if (ent.equals("00"))
			return true;
		else
			return false;

	}

	public void setListCompare(List<String> listCompare) {
		this.listCompare = listCompare;
	}

	public List<String> getListCompare() {
		this.listCompare = new ArrayList<String>(Arrays.asList(mun.split(",")));
		return listCompare;
	}

	public int getTypeSector() {
		if (String.valueOf(id).length() == 1)
			this.typeSector = 1;
		else if (String.valueOf(id).length() == 2)
			this.typeSector = 2;
		else
			this.typeSector = 3;
		return typeSector;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
