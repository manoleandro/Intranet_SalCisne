package wsr.model;
import java.util.List;


public class DocsRest<T> {
	
	private String docs;
	private List<T> list;

	public DocsRest(String docs, List<T> list) {
		this.docs = docs;
		this.list = list;
	}

	public String getDocs() {
		return docs;
	}
	
	public void setDocs(String docs) {
		this.docs = docs;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}	