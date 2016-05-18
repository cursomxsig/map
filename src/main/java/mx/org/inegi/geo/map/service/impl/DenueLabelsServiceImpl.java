package mx.org.inegi.geo.map.service.impl;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Contact;
import mx.org.inegi.geo.map.mapper.DenueLabelMapper;
import mx.org.inegi.geo.map.model.LabelInfo;
import mx.org.inegi.geo.map.service.DenueLabelsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenueLabelsServiceImpl implements DenueLabelsService {
	@Autowired
	DenueLabelMapper mappe;

	@Override
	public Map<String, Object> getLabelInfo(LabelInfo labelInfo) {
		Map<String, Object> info = mappe.getLabelInfo(labelInfo);
		return info;
	}

	@Override
	public Map<String, Object> getLabelDetail(LabelInfo labelInfo) {
		Map<String, Object> detail = mappe.getLabelDetail(labelInfo);
		return detail;
	}

	@Override
	public Contact getLabelContact(LabelInfo labelInfo) {
		Contact contact = mappe.getLabelContact(labelInfo);
		return contact;
	}

	@Override
	public Map<String, Object> getLabelInfoByGeom(LabelInfo labelInfo) {
		Map<String, Object> info = mappe.getLabelInfoByGeom(labelInfo);
		return info;
	}

	@Override
	public Map<String, Object> getLabelDetailByGeom(LabelInfo labelInfo) {
		Map<String, Object> detail = mappe.getLabelDetailByGeom(labelInfo);
		return detail;
	}

	@Override
	public Contact getLabelContactGeom(LabelInfo labelInfo) {
		Contact contact = mappe.getLabelContactGeom(labelInfo);
		return contact;
	}
}
