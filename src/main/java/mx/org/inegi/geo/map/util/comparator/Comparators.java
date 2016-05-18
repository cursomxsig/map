package mx.org.inegi.geo.map.util.comparator;

import java.util.Comparator;

import mx.org.inegi.geo.map.model.TableFieldType;
import mx.org.inegi.geo.map.xml.model.FieldFunction;
import mx.org.inegi.geo.map.xml.model.Server;
import mx.org.inegi.geo.map.xml.model.Table;

/**
 * @author yan.luevano
 * 
 */
public class Comparators {

	public static class TableComparators {
		static private class NameComparator implements Comparator<Table> {
			public int compare(Table o1, Table o2) {
				return o1.getName().compareTo(o2.getName());
			}
		}

		static private class SchemaComparator implements Comparator<Table> {
			public int compare(Table o1, Table o2) {
				return o1.getSchema().compareTo(o2.getSchema());
			}
		}

		static private class ServerComparator implements Comparator<Table> {
			public int compare(Table o1, Table o2) {
				return o1.getServer().compareTo(o2.getServer());
			}
		}

		public static Comparator<Table> getComparatorByName() {
			return new NameComparator();
		}

		public static Comparator<Table> getComparatorBySchema() {
			return new SchemaComparator();
		}

		public static Comparator<Table> getComparatorByServer() {
			return new ServerComparator();
		}

	}

	public static class ServerComparators {
		static private class AliasComparator implements Comparator<Server> {
			public int compare(Server o1, Server o2) {
				int alias = o1.getAlias().compareTo(o2.getAlias());
				int ip = o1.getServer().compareTo(o2.getServer());
				int port = o1.getPort().compareTo(o2.getPort());
				if (alias == 0 && ip == 0 && port == 0)
					return 0;
				else
					return -1;
			}
		}

		public static Comparator<Server> getComparatorByAlias() {
			return new AliasComparator();
		}

	}

	public static class FieldComparators {
		static private class FunctionOrderComparator implements
				Comparator<FieldFunction> {

			public int compare(FieldFunction o1, FieldFunction o2) {
				return o2.getOrder() - o1.getOrder();
			}

		}

		public static Comparator<FieldFunction> getComparatorByFunctionOrder() {
			return new FunctionOrderComparator();
		}

	}

	public static class TableFieldTypeComparators {
		static private class FieldAliasComparator implements
				Comparator<TableFieldType> {

			public int compare(TableFieldType o1,
					TableFieldType o2) {
				return o1.getAlias().compareTo(o2.getAlias());
			}

		}

		public static Comparator<TableFieldType> getComparatorByAlias() {
			return new FieldAliasComparator();
		}
	}

}
