package mx.org.inegi.geo.map.connection.holder;

/**
 * Class meant to be used by MapConnectionDataSource.
 * 
 * @author femat
 * 
 */
public class ConnectionContextHolder {

	public static class ConnectionInfo {

		String alias;

		String project;

		boolean readOnly;

		public ConnectionInfo(String alias) {
			super();
			this.alias = alias;
			this.project = null;
			this.readOnly = false;
		}

		public ConnectionInfo(String alias, String project) {
			super();
			this.alias = alias;
			this.project = project;
			this.readOnly = false;
		}

		public ConnectionInfo(String alias, boolean readOnly) {
			super();
			this.alias = alias;
			this.project = null;
			this.readOnly = readOnly;
		}

		public ConnectionInfo(String alias, String project, boolean readOnly) {
			super();
			this.alias = alias;
			this.project = project;
			this.readOnly = readOnly;
		}

		public String getAlias() {
			return this.alias;
		}

		public String getProject() {
			return this.project;
		}

		public boolean isReadOnly() {
			return this.readOnly;
		}

	}

	private static ThreadLocal<ConnectionInfo> info = new ThreadLocal<ConnectionInfo>();

	public static ConnectionInfo getConnectionInfo() {
		return info.get();
	}

	public static void setConnectionInfo(String alias) {
		ConnectionInfo connectionInfo = new ConnectionInfo(alias);
		info.set(connectionInfo);
	}

	public static void setConnectionInfo(String alias, String project) {
		ConnectionInfo connectionInfo = new ConnectionInfo(alias, project);
		info.set(connectionInfo);
	}

	public static void setConnectionInfo(String alias, boolean readOnly) {
		ConnectionInfo connectionInfo = new ConnectionInfo(alias, readOnly);
		info.set(connectionInfo);
	}

	public static void setConnectionInfo(String alias, String project,
			boolean readOnly) {
		ConnectionInfo connectionInfo = new ConnectionInfo(alias, project,
				readOnly);
		info.set(connectionInfo);
	}

	public static void resetInfo() {
		info.remove();
	}

}
