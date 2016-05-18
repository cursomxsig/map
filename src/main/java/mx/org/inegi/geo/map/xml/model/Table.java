package mx.org.inegi.geo.map.xml.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author yan.luevano
 */
@JsonAutoDetect(getterVisibility = Visibility.NONE, fieldVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class Table implements Cloneable {

	private String schema;
	private String name;
	private String alias;
	private String database;
	private String server;
	private String geometry;
	private String projection;
	private String rasterAlias;
	private List<SearchField> searchFields;
	private List<String> orderByFields;
	protected List<String> projects;
	private boolean search;
	private boolean identify;
	private boolean buffer;
	private boolean raster;
	private String userAlias;
	@JsonProperty
	private TableFields fields;
	private Totals totals;
	private Totals export;
	private Resolution resolution;
	private float iconSize;

	public Table(String schema, String name, String alias, String database,
			String server, String geometry, String projection,
			String rasterAlias, TableFields fields, Totals totals,Totals export,
			Resolution resolution, String iconSize) {
		this.schema = schema;
		this.name = name;
		this.alias = alias;
		this.database = database;
		this.server = server;
		this.geometry = geometry;
		this.projection = projection;
		this.rasterAlias = rasterAlias;
		this.fields = fields;
		this.totals = totals;
		this.export=export;
		this.resolution = resolution;
		try {
			int is = Integer.parseInt(iconSize);
			this.iconSize = is;
		} catch (NumberFormatException ex) {
			this.iconSize = 5;
		}
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setTableFields(TableFields fields) {
		this.fields = fields;
	}

	public TableFields getFields() {
		return fields;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Table) {
			Table t = (Table) obj;
			return t.getDatabase().equalsIgnoreCase(database)
					&& t.getName().equalsIgnoreCase(name)
					&& t.getSchema().equalsIgnoreCase(schema)
					&& t.getServer().equalsIgnoreCase(server);
		}
		return false;
	}

	@Override
	public Object clone() {
		Table obj = null;
		try {
			obj = (Table) super.clone();
			obj.fields = (TableFields) fields.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * @return the searchField
	 */
	public List<SearchField> getSearchFields() {
		return searchFields;
	}

	/**
	 * @param searchField
	 *            the searchField to set
	 */
	public void setSearchFields(List<SearchField> searchFields) {
		this.searchFields = searchFields;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @return the orderByFields
	 */
	public List<String> getOrderByFields() {
		return orderByFields;
	}

	public boolean hasOrderByFields() {
		return orderByFields != null && orderByFields.size() > 0;
	}

	/**
	 * @param orderByFields
	 *            the orderByFields to set
	 */
	public void setOrderByFields(List<String> orderByFields) {
		this.orderByFields = orderByFields;
	}

	/**
	 * @return the search
	 */
	public boolean isSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(boolean search) {
		this.search = search;
	}

	/**
	 * @return the identify
	 */
	public boolean isIdentify() {
		return identify;
	}

	/**
	 * @param identify
	 *            the identify to set
	 */
	public void setIdentify(boolean identify) {
		this.identify = identify;
	}

	/**
	 * @return the userAlias
	 */
	public String getUserAlias() {
		return userAlias;
	}

	/**
	 * @param userAlias
	 *            the userAlias to set
	 */
	public void setUserAlias(String aliasUsuario) {
		this.userAlias = aliasUsuario;
	}

	/**
	 * @return the projects
	 */
	public List<String> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(List<String> projects) {
		this.projects = projects;
	}

	/**
	 * @return the buffer
	 */
	public boolean isBuffer() {
		return buffer;
	}

	/**
	 * @param buffer
	 *            the buffer to set
	 */
	public void setBuffer(boolean buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return the raster
	 */
	public boolean isRaster() {
		return raster;
	}

	/**
	 * @param raster
	 *            the raster to set
	 */
	public void setRaster(boolean raster) {
		this.raster = raster;
	}

	public String getGeometry() {
		return geometry;
	}

	public String getProjection() {
		return projection;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public float getIconSize() {
		return iconSize;
	}

	public void setIconSize(String iconSize) {
		try {
			float is = Float.parseFloat(iconSize);
			this.iconSize = is;
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
			this.iconSize = 5.0f;
		}
	}

	/**
	 * @return the totals
	 */
	public Totals getTotals() {
		return totals;
	}
	


	public Totals getExport() {
		return export;
	}

	/**
	 * @return the rasterAlias
	 */
	public String getRasterAlias() {
		return rasterAlias;
	}

	/**
	 * @param rasterAlias
	 *            the rasterAlias to set
	 */
	public void setRasterAlias(String rasterAlias) {
		this.rasterAlias = rasterAlias;
	}

}
