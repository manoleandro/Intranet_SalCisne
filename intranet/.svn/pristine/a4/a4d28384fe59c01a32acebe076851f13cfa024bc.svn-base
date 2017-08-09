package br.com.sp.intranet.model.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "channel", propOrder = { "title", "link", "description", "category", "ttl", "item" })
public class Channel {

	@XmlElement(required = true)
	protected String title;
	@XmlElement(required = true)
	protected String link;
	@XmlElement(required = true)
	protected String description;
	@XmlElement(required = true)
	protected String category;
	@XmlElement(required = true)
	protected String ttl;
	protected List<Item> item;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public List<Item> getItem() {
		if (item == null) {
			item = new ArrayList<Item>();
		}
		return this.item;
	}

}
