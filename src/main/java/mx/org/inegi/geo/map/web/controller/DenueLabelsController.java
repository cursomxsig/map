package mx.org.inegi.geo.map.web.controller;

import java.util.Map;

import javax.validation.Valid;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Contact;
import mx.org.inegi.geo.map.domain.Label;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.LabelInfo;
import mx.org.inegi.geo.map.service.DenueLabelsService;
import mx.org.inegi.geo.map.utils.DenueUtils;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.xml.loader.ServerData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("denue")
public class DenueLabelsController {

	private final String DEFAULT_TABLE = "cdenue";

	@Autowired
	DenueLabelsService service;

	@Autowired
	private ServerData serverData;

	@GzipResponse
	@RequestMapping(value = "label/id", method = RequestMethod.POST)
	public Object getLabelById(@RequestBody LabelInfo labelinfo)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		Map<String, Object> info = service.getLabelInfo(labelinfo);
		Label label = new Label();
		DenueUtils util = new DenueUtils();
		label.setInfo(util.convertToField(info));
		if (info != null) {
			Contact contact = service.getLabelContact(labelinfo);
			label.setContact(contact);
			label.setColor(contact.getColor());
			return ResponseFactory.successfulResponse("Label", label);
		} else
			return ResponseFactory.unsuccessfulResponse();
	}

	@GzipResponse
	@RequestMapping(value = "label/id/detail", method = RequestMethod.POST)
	public Object getLabelDetailById(@RequestBody LabelInfo labelinfo)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		Map<String, Object> detail = service.getLabelDetail(labelinfo);
		if (detail != null) {
			Label label = new Label();
			DenueUtils util = new DenueUtils();
			label.setDetail(util.convertToField(detail));
			return ResponseFactory.successfulResponse("Label", label);
		} else
			return ResponseFactory.unsuccessfulResponse();
	}

	@GzipResponse
	@RequestMapping(value = "label/geom", method = RequestMethod.POST)
	public Object getLabelByGeometry(@Valid @RequestBody LabelInfo labelinfo)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		Map<String, Object> info = service.getLabelInfoByGeom(labelinfo);
		Label label = new Label();
		DenueUtils util = new DenueUtils();
		if (info != null) {
			label.setInfo(util.convertToField(info));
			Contact contact = service.getLabelContactGeom(labelinfo);
			label.setContact(contact);
			label.setColor(contact.getColor());
			return ResponseFactory.successfulResponse("Label", label);
		} else
			return ResponseFactory.unsuccessfulResponse();
	}

	@GzipResponse
	@RequestMapping(value = "label/geom/detail", method = RequestMethod.POST)
	public Object getLabelDetailByGeometry(@RequestBody LabelInfo labelInfo)
			throws Exception {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		Map<String, Object> detail = service.getLabelDetailByGeom(labelInfo);
		if (detail != null) {
			Label label = new Label();
			DenueUtils util = new DenueUtils();
			label.setDetail(util.convertToField(detail));
			return ResponseFactory.successfulResponse("Label", label);
		} else
			return ResponseFactory.unsuccessfulResponse();
	}
}
