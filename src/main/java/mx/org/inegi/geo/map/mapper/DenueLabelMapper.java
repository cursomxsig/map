package mx.org.inegi.geo.map.mapper;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Contact;
import mx.org.inegi.geo.map.model.LabelInfo;

public interface DenueLabelMapper {

	public Map<String, Object> getLabelInfo(LabelInfo labelInfo);

	public Map<String, Object> getLabelDetail(LabelInfo labelInfo);

	public Contact getLabelContact(LabelInfo labelInfo);

	public Map<String, Object> getLabelInfoByGeom(LabelInfo labelInfo);

	public Map<String, Object> getLabelDetailByGeom(LabelInfo labelInfo);

	public Contact getLabelContactGeom(LabelInfo labelInfo);

}
