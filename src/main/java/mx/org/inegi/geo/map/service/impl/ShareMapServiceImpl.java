package mx.org.inegi.geo.map.service.impl;

import java.util.Map;

import mx.org.inegi.geo.map.domain.Mail;
import mx.org.inegi.geo.map.domain.Share;
import mx.org.inegi.geo.map.mapper.ShareMapMapper;
import mx.org.inegi.geo.map.model.Mailinfo;
import mx.org.inegi.geo.map.service.ShareMapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareMapServiceImpl implements ShareMapService {

	@Autowired
	ShareMapMapper mapper;

	@Autowired
	private Mail mail;

	// @Autowired
	// private MailSender mailSender;

	@Override
	public Long add(Share share) {
		mapper.add(share);
		return share.getId();
	}

	@Override
	public Map<String, Object> find(int id) {
		Map<String, Object> json = mapper.find(id);
		return json;
	}

	@Override
	public void emailSpring(Mailinfo info) {
		this.mail.sendSpringMail(info);
	}

	@Override
	public void emailSimpleMail(Mailinfo info) {
		this.mail.sendSimpleMail(info);

	}

}
