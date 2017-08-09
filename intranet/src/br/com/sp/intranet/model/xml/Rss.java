package br.com.sp.intranet.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rss", propOrder = { "channel" }, namespace = "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'")
public class Rss {

	@XmlElement(required = true)
	protected Channel channel;
	@XmlAttribute
	@XmlSchemaType(name = "anySimpleType")
	protected String version;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel value) {
		this.channel = value;
	}

	public String getVersion() {
		if (version == null) {
			return "2.0";
		} else {
			return version;
		}
	}

	public void setVersion(String value) {
		this.version = value;
	}

}
