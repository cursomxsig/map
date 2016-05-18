package mx.org.inegi.geo.map.service;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Share;
import mx.org.inegi.geo.map.model.Mailinfo;

public interface ShareMapService {

	public Long add(Share share);

	public Map<String, Object> find(int id);

	public void emailSpring(Mailinfo info);

	public void emailSimpleMail(Mailinfo info);
}
