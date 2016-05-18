/**
 * 
 */
package mx.org.inegi.geo.map.utils;

import mx.org.inegi.geo.map.model.SectorTable;
import mx.org.inegi.geo.map.model.ThemeInfo;

/**
 * @author femat
 *
 */
public class SensorTableUtil {

	private ThemeInfo info;
	private int type;

	public SensorTableUtil(ThemeInfo themeInfo, int type) {
		this.info = themeInfo;
		this.type = type;
	}

	public String getSchemaAndTable() {
		String ent = info.getEnt();
		String table = null;
		StringBuilder sb = new StringBuilder();
		if (ent.equalsIgnoreCase("00")&&ent.length()==2) {
			switch (type) {
			case 1:
				sb.append(SectorTable.ENTIDAD_GRANSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.ENTIDAD_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println(table);
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.ENTIDAD_SECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.ENTIDAD_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.ENTIDAD_SUBSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.ENTIDAD_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		} 
		
		if (!ent.equalsIgnoreCase("00")&&ent.length()==2) {
			switch (type) {
			case 1:
				sb.append(SectorTable.MUNICIPAL_GRANSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.MUNICIPAL_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.MUNICIPAL_SECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.MUNICIPAL_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.MUNICIPAL_SUBSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.MUNICIPAL_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		
		if (!ent.equalsIgnoreCase("00")&&ent.length()==5) {
			switch (type) {
			case 1:
				sb.append(SectorTable.LOC_GRANSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.LOC_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.LOC_SECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.LOC_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.LOC_SUBSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.LOC_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		
		if (!ent.equalsIgnoreCase("00")&&ent.length()==9) {
			switch (type) {
			case 1:
				sb.append(SectorTable.AGEB_GRANSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.AGEB_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.AGEB_SECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.AGEB_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.AGEB_SUBSECTOR.schema());
				sb.append(".");
				sb.append(SectorTable.AGEB_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		
		return table;
	}

	public String getTable4Indicator() {
		String ent = info.getEnt();
		String table = null;
		StringBuilder sb = new StringBuilder();
		if (ent.equalsIgnoreCase("00")&&ent.length()==2) {
			switch (type) {
			case 1:
				sb.append(SectorTable.ENTIDAD_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println(table);
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.ENTIDAD_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:	
				sb.append(SectorTable.ENTIDAD_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		} 
		
		if (!ent.equalsIgnoreCase("00")&&ent.length()==2) {
			switch (type) {
			case 1:		
				sb.append(SectorTable.MUNICIPAL_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.MUNICIPAL_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.MUNICIPAL_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		
		if (!ent.equalsIgnoreCase("00")&&ent.length()==5) {
			switch (type) {
			case 1:
				sb.append(SectorTable.LOC_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.LOC_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.LOC_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		
		if (!ent.equalsIgnoreCase("00")&&ent.length()==9) {
			switch (type) {
			case 1:
				sb.append(SectorTable.AGEB_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.AGEB_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.AGEB_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		
		return table;
		
		/*String ent = info.getEnt();
		String table = null;
		StringBuilder sb = new StringBuilder();
		if (ent.equalsIgnoreCase("00")) {
			switch (type) {
			case 1:
				sb.append(SectorTable.NACIONAL_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.NACIONAL_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.NACIONAL_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		} else {
			switch (type) {
			case 1:
				sb.append(SectorTable.ENTIDAD_GRANSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 2:
				sb.append(SectorTable.ENTIDAD_SECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			case 3:
				sb.append(SectorTable.ENTIDAD_SUBSECTOR.table());
				sb.append(info.getYear());
				table = sb.toString();
				System.out.println("------------------------------------------"+table);
				break;
			}
		}
		return table;*/
	}

} 

