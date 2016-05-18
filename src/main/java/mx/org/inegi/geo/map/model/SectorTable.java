/**
 * 
 */
package mx.org.inegi.geo.map.model;

/**
 * @author femat
 *
 */
public enum SectorTable {
	
	NACIONAL_GRANSECTOR("ce2014", "nacional_gransector_"),
	NACIONAL_SECTOR("ce2014", "nacional_sector_"),
	NACIONAL_SUBSECTOR("ce2014", "nacional_subsector_"),
	
	ENTIDAD_GRANSECTOR("ce2014", "entidad_gransector_"),
	ENTIDAD_SECTOR("ce2014", "entidad_sector_"),
	ENTIDAD_SUBSECTOR("ce2014", "entidad_subsector_"),

	MUNICIPAL_GRANSECTOR("ce2014", "municipal_gransector_"),
	MUNICIPAL_SECTOR("ce2014", "municipal_sector_"),
	MUNICIPAL_SUBSECTOR("ce2014", "municipal_subsector_"),

	LOC_GRANSECTOR("ce2014", "loc_gransector_"),
	LOC_SECTOR("ce2014", "loc_sector_"),
	LOC_SUBSECTOR("ce2014", "loc_subsector_"),
	
	AGEB_GRANSECTOR("ce2014", "ageb_gransector_"),
	AGEB_SECTOR("ce2014", "ageb_sector_"),
	AGEB_SUBSECTOR("ce2014", "ageb_subsector_");
	
	private final String schema;
	private final String table;

	SectorTable(String schema, String table) {
		this.schema = schema;
		this.table = table;
	}
	
	public String schema(){
		return schema;
	}
	
	public String table(){
		return table;
	}

} 

