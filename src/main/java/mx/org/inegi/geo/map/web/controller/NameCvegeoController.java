package mx.org.inegi.geo.map.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.NameCvegeo;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.NameCvegeoInfo;
import mx.org.inegi.geo.map.service.NameCvegeoService;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

@RestController
@RequestMapping("namecvegeo")
public class NameCvegeoController {
	private final String DEFAULT_TABLE = "cdenue";
	
	@Autowired
	private NameCvegeoService service;
	
	private List <NameCvegeo> ls=new ArrayList<NameCvegeo>();
	public String cvegeo;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object tracking(@Valid @RequestBody NameCvegeoInfo nameCevegeoInfo){
		cvegeo=nameCevegeoInfo.getCvegeo();
		
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE, true);
		System.out.println(cvegeo);
		if(cvegeo.equals("00")&&cvegeo.length()==2)
		{
			ls=service.cvName(cvegeo, "", "");//nacional 00 return 0X
			return ResponseFactory.successfulResponse()
					.addField("Tipo", "nacional")
					.addField("Estados", ls);
		}
		if(!cvegeo.equals("00")&&cvegeo.length()==2)
		{
			ls=service.cvName(cvegeo, "", "");//municipal 02 return 0X00X
			return ResponseFactory.successfulResponse()
					.addField("Tipo", "Municipal")
					.addField("Nombre", service.geoName("ent", cvegeo))
					.addField("Extent", service.extent("ent", cvegeo))
					.addField("Municipios", ls);
		}
		if(!cvegeo.equals("00")&&cvegeo.length()==5)
		{
			ls=service.cvName(cvegeo.substring(0,2), cvegeo.substring(2), "");//localidad 02001 return 0X00X000X
			return ResponseFactory.successfulResponse()
					.addField("Tipo", "Localidades")
					.addField("Nombre", service.geoName("mun", cvegeo))
					.addField("Extent", service.extent("mun", cvegeo))
					.addField("Localidades", ls);
		}
		if(!cvegeo.equals("00")&&cvegeo.length()==9)
		{
			ls=service.cvName(cvegeo.substring(0,2), cvegeo.substring(2,5), cvegeo.substring(5));//localidad 020010001 return 0X00X000X000X
			return ResponseFactory.successfulResponse()
					.addField("Tipo", "Agebs")
					.addField("Nombre", service.geoName("loc", cvegeo))
					.addField("Extent", service.extent("loc", cvegeo))
					.addField("Agebs", ls);
		}
		
		return ResponseFactory.unsuccessfulResponse("Error de clave geografica");
	}
} 

