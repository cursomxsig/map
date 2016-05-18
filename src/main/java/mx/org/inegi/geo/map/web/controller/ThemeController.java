package mx.org.inegi.geo.map.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.org.inegi.geo.map.connection.holder.ConnectionContextHolder;
import mx.org.inegi.geo.map.domain.Theme;
import mx.org.inegi.geo.map.exception.TimeFrameException;
import mx.org.inegi.geo.map.gzip.GzipResponse;
import mx.org.inegi.geo.map.model.ColorUpdate;
import mx.org.inegi.geo.map.model.ThemeInfo;
import mx.org.inegi.geo.map.model.ThemeMinMax;
import mx.org.inegi.geo.map.service.ThemeService;
import mx.org.inegi.geo.map.utils.TimeFrameUtil;
import mx.org.inegi.geo.map.web.response.ResponseFactory;

@RestController
@RequestMapping("theme")
public class ThemeController {

	private final String DEFAULT_TABLE = "cdenue";

	@Autowired
	private ThemeService service;

	@Autowired
	private TimeFrameUtil timeFrame;

	@GzipResponse
	@RequestMapping(method = RequestMethod.POST)
	public Object tracking(@Valid @RequestBody ThemeInfo themeInfo) throws TimeFrameException {
		timeFrame.isValid(themeInfo.getYear());
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE);
		int type = service.findType(themeInfo.getSector());
		Theme theme = service.add(themeInfo, type);
		double indicator = service.findIndicator(themeInfo, type);
		Map<String, Object> minNmax = service.findMinAndMax(themeInfo, type);
		Object min = minNmax.get("min");
		Object max = minNmax.get("max");
		if (theme.getSuccess()) {
			if(themeInfo.getTipoConsulta().equals("nei"))
			{
			return ResponseFactory.successfulResponse("id", theme.getId())
					.addField("indicator", indicator)
					.addField("boundaries", theme.getBoundaries())
					.addField("mean", theme.getMean()) 
					.addField("median", theme.getMedian())
					.addField("sd", theme.getStandardDeviation())
					.addField("mode", theme.getMode())
					.addField("n", theme.getN()).addField("min", min)
					.addField("max", max);
			}
			if(!themeInfo.getTipoConsulta().equals("nei"))
			{
				List<ThemeMinMax> minMaxList=theme.getMinMaxList();
				ThemeMinMax temp=new ThemeMinMax();
				
				for(int i=0;i<minMaxList.size();i++)
				{
					temp=minMaxList.get(i);
					
					temp.setCvegeo(vecToCad(service.findCevegeo(temp.getMin(), temp.getMax(), theme.getTable(), themeInfo.getVariable(), themeInfo.getSector(), themeInfo.getEnt())));
					minMaxList.set(i, temp);
				}
				theme.setMinMaxList(minMaxList); 
			return ResponseFactory.successfulResponse("id", theme.getId())
					.addField("indicator", indicator)
					.addField("boundaries", theme.getMinMaxList())
					.addField("mean", theme.getMean()) 
					.addField("median", theme.getMedian())
					.addField("sd", theme.getStandardDeviation())
					.addField("mode", theme.getMode())
					.addField("n", theme.getN()).addField("min", min)
					.addField("max", max);
			}
		
		} 
		
		else {
			return ResponseFactory.unsuccessfulResponse(theme.getMessage());
		}
		return max;
	}

	
	public String vecToCad(String[] vec){
		String cad="";
		for(int i=0;i<vec.length;i++)
		{
			cad=cad+vec[i]+",";
		}
		return cad.substring(0, cad.length()-1);
	}
	
	
	@GzipResponse
	@RequestMapping(value = "colors", method = RequestMethod.POST)
	public Object colors(@Valid @RequestBody ColorUpdate cu) {
		ConnectionContextHolder.setConnectionInfo(DEFAULT_TABLE);
		boolean success = service.updateColors(cu);
		if (success)
			return ResponseFactory.successfulResponse();
		else
			return ResponseFactory.unsuccessfulResponse("Theme not valid.");
	}

} 

