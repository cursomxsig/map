package mx.org.inegi.geo.map.connection;

import java.sql.Connection;
import java.sql.SQLException;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder.ConnectionInfo;
import mx.org.inegi.geo.map.exception.InvalidProjectException;
import mx.org.inegi.geo.map.exception.TableNotFoundException;
import mx.org.inegi.geo.map.xml.loader.ServerData;
import mx.org.inegi.geo.map.xml.model.Server;
import mx.org.inegi.geo.map.xml.model.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.Assert;

/**
 * 
 * @author femat
 * 
 */
public class MapConnectionDataSource extends AbstractDataSource {

	@Autowired
	private ConnectionManager connectionManager;

	@Autowired
	private ServerData serverData;

	@Override
	public Connection getConnection() throws SQLException {
		ConnectionInfo connectionInfo = ConnectionContextHolder
				.getConnectionInfo();
		Assert.notNull(connectionInfo.getAlias(),
				"ConnectionInfo [alias] cannot be null!");
		Connection conn = null;
		
		try {
			if (connectionInfo.getProject() == null) {
				conn = getConnectionByAlias(connectionInfo);
			} else {
				conn = getConnectionByAliasAndProject(connectionInfo);
			}
		} catch (InvalidProjectException e) {
			e.getMessage();
		} catch (TableNotFoundException e) {
			e.getMessage();
		}
		return conn;
	}
	

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return getConnection();
	}

	private Connection getConnectionByAlias(ConnectionInfo connectionInfo)
			throws SQLException, TableNotFoundException {
		Table t = serverData.findTable(connectionInfo.getAlias());
		Server server = serverData.findServerByTable(connectionInfo
				.getAlias());
		server.setDatabase(t.getDatabase());
		if (connectionInfo.isReadOnly()) {
			return connectionManager.getReadOnlyConnection(server);
		} else {
			return connectionManager.getWritingConnection(server);
		}

	}

	private Connection getConnectionByAliasAndProject(
			ConnectionInfo connectionInfo) throws SQLException,
			InvalidProjectException, TableNotFoundException {
		String alias = connectionInfo.getAlias();
		String project = connectionInfo.getProject();
		Table t = serverData.findTable(alias, project);
		Server server = serverData
				.findServerByTableAndProject(alias, project);
		server.setDatabase(t.getDatabase());
		if (connectionInfo.isReadOnly()) {
			return connectionManager.getReadOnlyConnection(server);
		} else {
			return connectionManager.getWritingConnection(server);
		}

	}

}
