/**
 * 
 */
package mx.org.inegi.geo.map.domain;

import java.util.ArrayList;
import java.util.List;

import mx.org.inegi.geo.map.model.ThemeBoundary;
import mx.org.inegi.geo.map.model.ThemeInfo;
import mx.org.inegi.geo.map.model.ThemeMinMax;

/**
 * @author femat
 *
 */
public class Theme {

	

	private Long id;

	private ThemeInfo info;

	private String table;


	private String definition;

	private String[] elements;

	private List<ThemeBoundary> boundaries = new ArrayList<ThemeBoundary>();

	private List<ThemeMinMax> minMaxList = new ArrayList<ThemeMinMax>(); 
	
	private final String[] colors = { "251 242 140", "220 187 106",
			"190 132 71", "159 76 37", "128 21 2" };
	//-------------------
	
	public Theme(ThemeInfo info, String table, String definition) {
		this.info = info;
		this.table = table;
		this.definition = definition;
		this.elements = definition.split("#");
		if(!info.getTipoConsulta().equals("nei"))//si consulta es N.E.I, no hace nado de lo contrario la reestructura
		{
			this.definition="";//limpia para no almacenar basura
			this.definition=createDefinition(elements[1]);
			createMinMax();
		}
		
		if (getSuccess()&&info.getTipoConsulta().equals("nei"))
			createBoundaries();
	}
	
	private String createDefinition(String elements){
		String[] minMax = elements.replaceAll(";", ",").split(",");//elimina punto y coma, divide en vector de forma: [min][max][elementos]
		String def="";//definicion a retornar
		int indexColors=0,minMaxIndex=0;//indices para colores RGB y para vector minMAx
		for(String m : minMax)//recorre vector con minimos maximos y elementos
		{
			if(minMaxIndex<2)//toma minimos/maximos y omite num de elementos
			{
				def=def+m+"-";
				minMaxIndex++;
			}
			else if (minMaxIndex>=2)//valida si ya tiene un minimo y un maximo y pone el color RGB correspondiente
			{
				def=def+colors[indexColors]+":";
				indexColors++;
				minMaxIndex=0;
			}
		}
		return def.substring(0, def.length()-1);//retorna la definicion nueva y corta dos puntos añadidos por automatizacion de proceso
	}
	//-----------------------
	
	private void createBoundaries() {
		String[] limits = elements[1].split(";");
		int i = 0;
		for (String l : limits) {

			boundaries.add(new ThemeBoundary(l, colors[i], i));
			//
			// boundaries.add(new ThemeBoundary("", "", 0));
			i++;
		}
	}
	
	private void createMinMax()
	{
		String[] limits = elements[1].split(";");
		int i = 0;
		for (String l : limits) {
			
			minMaxList.add(new ThemeMinMax(l, colors[i], i));
			//
			// boundaries.add(new ThemeBoundary("", "", 0));
			i++;
		}
	}
	
	public boolean getSuccess() {
		String s = elements[0];
		boolean success = Boolean.parseBoolean(s);
		return success;
	}

	public String getMessage() {
		String m = elements[1];
		return m;
	}

	public double getMean() {
		String m = elements[2];
		double mean = Double.parseDouble(m);
		return mean;
	}
 
	public double getMedian() {
		String md = elements[3];
		double median = Double.parseDouble(md);
		return median;
	}

	public double getStandardDeviation() {
		String sd = elements[4];
		double std = Double.parseDouble(sd);
		return std;
	}

	public double getMode() {
		String mod = elements[5];
		double mode = Double.parseDouble(mod);
		return mode;
	}

	public double getN() {
		String n = elements[6];
		double ne = Double.parseDouble(n);
		return ne;
	}

	public List<ThemeBoundary> getBoundaries() {
		return boundaries;
	}

	public String getWhen() {
		// String and = " and ";
		String when = " when ";
		String then = " then ";
		String andd  = " and ";
		String menQ = " <= ";
		String mayQ = " >= ";
		//String equals = "=";
		String quote = "'";
		String space = " "; 
	    String variable = info.getVariable();
	    String[] minMax = elements[1].replaceAll(";", ",").split(",");//elimina punto y coma, divide en vector de forma: [min][max][elementos]
	    //String def="";//definicion a retornar
	    String m="";
		// int i = 0;
	    int indexColors=0,minMaxIndex=0;//indices para colores RGB y para vector minMAx
		StringBuilder sb = new StringBuilder();
		
		if(info.getTipoConsulta().equals("nei"))
		{
			for (ThemeBoundary b : boundaries) {
				/*
				 * sb.append(when); sb.append(variable); if (i == 0) {
				 * sb.append(gt); sb.append(equals); } else { sb.append(gt); }
				 * sb.append(b.getLower()); sb.append(and); sb.append(variable);
				 * sb.append(lt); sb.append(equals); sb.append(b.getUpper());
				 * sb.append(then); sb.append(quote); sb.append(b.getRgb());
				 * sb.append(quote); i++;
				 */
				sb.append(when);
				sb.append("b.cvegeo");
				sb.append(" in");
				sb.append("(");
				sb.append(b.getCvegeo());
				sb.append(")");
				sb.append(then);
				sb.append(quote);
				sb.append(b.getRgb());
				sb.append(quote);
			}
		}
		else if(!info.getTipoConsulta().equals("nei"))
		{
			for(int i=0;i<minMax.length;i++)//recorre vector con minimos maximos y elementos
			{
				
				
				if(minMaxIndex==0)//toma minimos/maximos y omite num de elementos
				{
					m=minMax[i];
					sb.append(when);
					sb.append(variable);
					sb.append(mayQ);
					sb.append(m);
					sb.append(andd);
					sb.append(variable);
					sb.append(menQ);
					//System.out.println("oooooooooooooooooooooooooooooooooooooooooooo"+sb.toString());
					minMaxIndex++;
					i++;
				}
				if(minMaxIndex==1)//toma minimos/maximos y omite num de elementos
				{
					m=minMax[i];
					sb.append(m);
					sb.append(then);
					minMaxIndex++;
					
				
				}
				else if (minMaxIndex==2)//valida si ya tiene un minimo y un maximo y pone el color RGB correspondiente
				{
					sb.append(quote);
					sb.append(colors[indexColors]);
					sb.append(quote);
					sb.append(space);
					indexColors++; 
					minMaxIndex=0;
				}
			}
			
		}

		return sb.toString();

	}

	public String getFilter() {
		StringBuilder sb = new StringBuilder(table);
		sb.append(".");
		sb.append("sector");
		sb.append("=");
		sb.append(info.getSector());
		if (!info.getEnt().equalsIgnoreCase("00")) {
			sb.append(" and ");
			sb.append(table);
			sb.append(".");
			sb.append("cve_ent");
			sb.append("=");
			sb.append("'");
			sb.append(info.getEnt());
			sb.append("'");
		}
		return sb.toString();
	}

	public String getTable() {
		return table;
	}

	public String getSignature() {
		String[] st = table.split("\\.");
		return st[0];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDefinition() {
		return definition;
	}
	public List<ThemeMinMax> getMinMaxList() {
		return minMaxList;
	}

	public void setMinMaxList(List<ThemeMinMax> minMaxList) {
		this.minMaxList = minMaxList;
	}

}
