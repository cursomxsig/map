package mx.org.inegi.geo.map.mapper;

import java.util.Map;

public interface ExportMapper {

	public void add(Map<String, Object> data);

	public String findById(int id);

}
