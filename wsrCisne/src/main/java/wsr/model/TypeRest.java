package wsr.model;

public class TypeRest {
	public String type;
	public Object object;

	public TypeRest(String type, Object object) {
		super();
		this.type = type;
		this.object = object;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}