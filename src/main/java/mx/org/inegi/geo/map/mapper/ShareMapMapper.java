package mx.org.inegi.geo.map.mapper;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Share;

public interface ShareMapMapper {
	public void add(Share share);

	public Map<String, Object> find(int id);

}
