/**
 * 
 */
package mx.org.inegi.geo.map.model;

import javax.validation.constraints.Min;

/**
 * @author femat
 *
 */
public class ThemeInfo {

	private String ent;

	private int sector;

	private String variable;
	
	private String tipoConsulta;
	
	private String cveEnt;
	
	private String cveMun;
	
	private String cveLoc;

	
	
	public String genCveEnt() {
	return ent.substring(0,2);	
		}
	public String genCveMun() {
		return ent.substring(2,5);	
		}
	public String genCveLoc() {
		return ent.substring(5,ent.length());	
		}
	
	public String getCveEnt() {
		return ent.substring(0,2);
	}

	public void setCveEnt(String cveEnt) {
		this.cveEnt = cveEnt;
	}

	public String getCveMun() {
		return ent.substring(2,5);
	}

	public void setCveMun(String cveMun) {
		this.cveMun = cveMun;
	}

	public String getCveLoc() {
		return ent.substring(5);
	}

	public void setCveLoc(String cveLoc) {
		this.cveLoc = cveLoc;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	@Min(2)
	private int estratos;

	private int year;

	public String getEnt() {
		return ent;
	}

	public void setEnt(String ent) {
		this.ent = ent;
	}

	public int getSector() {
		return sector;
	}

	public void setSector(int sector) {
		this.sector = sector;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public int getEstratos() {
		return estratos;
	}

	public void setEstratos(int estratos) {
		this.estratos = estratos;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
 
