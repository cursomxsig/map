package mx.org.inegi.geo.map.xml.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mx.org.inegi.geo.map.xml.access.AbstractXmlReader;
import mx.org.inegi.geo.map.xml.access.AliasDataConfigReader;
import mx.org.inegi.geo.map.xml.access.ServersXmlReader;
import mx.org.inegi.geo.map.xml.access.TablesXmlReader;
import mx.org.inegi.geo.map.xml.model.Document;
import mx.org.inegi.geo.map.xml.model.Server;
import mx.org.inegi.geo.map.xml.model.Table;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

/**
 *
 * @author femat
 * @author yan.luevano
 * 
 */
public class DataLoader {

	private static final Logger logger = Logger.getLogger(DataLoader.class);

	private List<Table> tables;
	private List<Server> servers;
	private Set<String> projects;
	private ServerData serverData;
	private Resource aliasFile;
	private Resource serversFile;

	public DataLoader() {
		serverData = new ServerData();
		projects = new TreeSet<String>();
		tables = new ArrayList<Table>();
	}

	public void loadData() {
		readTables();
		readServers();
		for (Server s : servers) {
			if (!serverData.data.containsKey(s)) {
				serverData.data.put(s, new ArrayList<Table>());
			}
			for (Table t : tables) {
				for (String project : t.getProjects()) {
					projects.add(project);
				}
				if (t.getServer().equalsIgnoreCase(s.getAlias())) {
					s.setDatabase(t.getDatabase());
					List<Table> tablas = serverData.data.get(s);
					tablas.add(t);
				}
			}
		}
		serverData.setProjects(projects);
		logger.info("Project(s): ");
		logger.info(projects);
	}

	private void readTables() {
		File xml = null;
		try {
			xml = aliasFile.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = xml.getParent() + File.separatorChar;
		AbstractXmlReader<Document> filesReader = new AliasDataConfigReader<Document>(
				xml, path);
		List<Document> files = filesReader.getData();
		for (Document f : files) {
			List<Table> tables = new ArrayList<Table>();
			AbstractXmlReader<Table> fileData = new TablesXmlReader<Table>(
					f.getFile());
			tables = fileData.getData();
			this.tables.addAll(tables);
		}
	}

	private void readServers() {
		File xml = null;
		try {
			xml = serversFile.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AbstractXmlReader<Server> fileData = new ServersXmlReader<Server>(xml);
		servers = fileData.getData();
	}

	/**
	 * Reloads data from xml files for tables and servers respectively
	 * 
	 */
	public void reloadData() {
		loadData();
	}

	/**
	 * @return the serverData
	 */
	public ServerData getServerData() {
		return serverData;
	}

	/**
	 * @return the projects
	 */
	public Set<String> getProjects() {
		return projects;
	}

	public void setAliasFile(Resource aliasFileName) {
		this.aliasFile = aliasFileName;
	}

	public void setServersFile(Resource serversFileName) {
		this.serversFile = serversFileName;
	}

}
