package mx.org.inegi.geo.map.domain;

import java.util.Collection;

import mx.org.inegi.geo.map.model.Field;

public class Label {
	private Collection<Field> info;

	private Collection<Field> detail;

	private Contact contact;

	private String color;

	public Collection<Field> getInfo() {
		return info;
	}

	public void setInfo(Collection<Field> info) {
		this.info = info;
	}

	public Collection<Field> getDetail() {
		return detail;
	}

	public void setDetail(Collection<Field> detail) {
		this.detail = detail;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
