package mx.org.inegi.geo.map.model;

public class Mailinfo {

	private String mail;

	private String url;

	private String from;

	private String subject;

	private String message;

	private String[] mails;

	private String name;

	public Mailinfo() {
		super();
		this.from = "Atencion.Usuarios@inegi.org.mx";
		this.subject = "Mapa Digital de México en línea";
		this.message = " te ha compartido un mapa para poderlo ver da clic en la siguiente liga: ";

	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mails = mail.split(",");
		this.mail = mail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.name + this.message + this.url;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getMails() {
		return mails;
	}

	public void setMails(String[] mails) {
		this.mails = mails;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
