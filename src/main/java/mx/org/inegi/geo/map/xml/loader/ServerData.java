package mx.org.inegi.geo.map.xml.loader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import mx.org.inegi.geo.map.exception.InvalidProjectException;
import mx.org.inegi.geo.map.exception.TableNotFoundException;
import mx.org.inegi.geo.map.model.TableFieldType;
import mx.org.inegi.geo.map.util.comparator.Comparators;
import mx.org.inegi.geo.map.xml.model.Server;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * 
 * @author femat
 * @author yan.luevano
 * 
 */
public class ServerData {

	protected TreeMap<Server, List<Table>> data;

	private Set<String> projects;

	public ServerData() {
		data = new TreeMap<Server, List<Table>>(
				Comparators.ServerComparators.getComparatorByAlias());
	}

	public Table findTable(String table) throws TableNotFoundException {
		for (List<Table> tbls : data.values()) {
			for (Table t : tbls) {
				if (t.getAlias().equalsIgnoreCase(table)
						|| t.getName().equalsIgnoreCase(table)) {
					return t;
				}
			}
		}
		throw new TableNotFoundException(table);
	}

	public Table findTable(String table, String project)
			throws InvalidProjectException, TableNotFoundException {
		if (project != null && project.trim().length() > 0
				&& isValidProject(project)) {
			for (List<Table> tables : data.values()) {
				for (Table t : tables) {
					if ((t.getAlias().equalsIgnoreCase(table) || t.getName()
							.equalsIgnoreCase(table))
							&& t.getProjects().contains(project)) {
						return t;
					}
				}
			}
		} else {
			return findTable(table);
		}
		throw new TableNotFoundException(table);
	}

	@Deprecated
	public Server findServerByTable(String table) {
		Server server = null;
		for (Map.Entry<Server, List<Table>> entry : data.entrySet()) {
			for (Table t : entry.getValue()) {
				if (t.getAlias().equalsIgnoreCase(table)
						|| t.getName().equalsIgnoreCase(table)) {
					server = entry.getKey();
					return server;
				}
			}
		}
		return null;
	}

	public Server findServerByTableAndProject(String table, String project) {
		Server server = null;
		for (Map.Entry<Server, List<Table>> entry : data.entrySet()) {
			for (Table t : entry.getValue()) {
				if ((t.getAlias().equalsIgnoreCase(table) || t.getName()
						.equalsIgnoreCase(table))
						&& t.getProjects().contains(project)) {
					server = entry.getKey();
					return server;
				}
			}
		}
		return null;
	}

	public Server getServer(String alias) {
		for (Map.Entry<Server, List<Table>> entry : data.entrySet()) {
			Server s = entry.getKey();
			if (s.getAlias().equalsIgnoreCase(alias)) {
				return s;
			}
		}
		return null;
	}

	void setProjects(Set<String> projects) {
		this.projects = projects;
	}

	public boolean isValidProject(String project)
			throws InvalidProjectException {
		if (project == null)
			return false;
		if (projects.contains(project)) {
			return true;
		} else {
			throw new InvalidProjectException("Not valid project " + project);
		}
	}

	public void setGeolocatorTableFromProyecto(String project) {
		for (Map.Entry<Server, List<Table>> entry : data.entrySet()) {
			for (Table t : entry.getValue()) {
				if (t.getAlias().toLowerCase().startsWith("geolocator")
						|| t.getName().toLowerCase().startsWith("geolocator")) {
					if (t.getProjects().contains(project)) {
						break;
					}
				}
			}
		}
	}

	public List<TableFieldType> getTableFieldTypes(String project) {
		List<TableFieldType> list = new ArrayList<TableFieldType>();
		for (Map.Entry<Server, List<Table>> entry : data.entrySet()) {
			for (Table t : entry.getValue()) {
				StringBuilder sb = new StringBuilder();
				List<String> projects = t.getProjects();
				if (projects.contains(project.toLowerCase())) {
					if (t.isSearch() || t.isIdentify() || t.isBuffer()) {
						TableFieldType ft = new TableFieldType();
						if (t.isSearch()) {
							sb.append("S");
						}
						if (t.isIdentify()) {
							sb.append("I");
						}
						if (t.isBuffer()) {
							sb.append("B");
						}
						ft.setType(sb.toString());
						ft.setName(t.getAlias());
						ft.setAlias(t.getUserAlias());
						list.add(ft);
					}
				}
			}
		}
		Collections.sort(list,
				Comparators.TableFieldTypeComparators.getComparatorByAlias());
		return list;
	}	
}
