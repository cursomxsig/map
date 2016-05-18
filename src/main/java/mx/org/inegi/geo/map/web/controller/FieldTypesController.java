/**
 * 
 */
package mx.org.inegi.geo.map.web.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.TableFieldType;
import mx.org.inegi.geo.map.web.response.ResponseFactory;
import mx.org.inegi.geo.map.web.response.ResponseFactory.SuccessfulResponse;
import mx.org.inegi.geo.map.xml.loader.ServerData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author femat
 *
 */
@RestController
public class FieldTypesController {

	@Autowired
	private ServerData serverData;

	@GzipResponse
	@RequestMapping(value = "fieldtypes", method = RequestMethod.POST)
	public Object getFieldTypes() {
		final String project = "mdm6";
		List<TableFieldType> fieldTypes = serverData
				.getTableFieldTypes(project);
		Collections.sort(fieldTypes, new Comparator<TableFieldType>() {
			@Override
			public int compare(TableFieldType o1, TableFieldType o2) {
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		SuccessfulResponse sr = ResponseFactory.successfulResponse(
				"fieldTypes", fieldTypes);
		return sr;
	}

}
