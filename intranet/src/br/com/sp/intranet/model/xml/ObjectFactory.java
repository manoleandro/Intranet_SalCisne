
package br.com.sp.intranet.model.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _Item_QNAME = new QName("", "item");
	private final static QName _Rss_QNAME = new QName("", "rss");
	private final static QName _Channel_QNAME = new QName("", "channel");

	public ObjectFactory() {
	}

	public Rss createRss() {
		return new Rss();
	}

	public Channel createChannel() {
		return new Channel();
	}

	public Item createItem() {
		return new Item();
	}

	@XmlElementDecl(namespace = "", name = "item")
	public JAXBElement<Item> createItem(Item value) {
		return new JAXBElement<Item>(_Item_QNAME, Item.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "rss")
	public JAXBElement<Rss> createRss(Rss value) {
		return new JAXBElement<Rss>(_Rss_QNAME, Rss.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "channel")
	public JAXBElement<Channel> createChannel(Channel value) {
		return new JAXBElement<Channel>(_Channel_QNAME, Channel.class, null, value);
	}

}
