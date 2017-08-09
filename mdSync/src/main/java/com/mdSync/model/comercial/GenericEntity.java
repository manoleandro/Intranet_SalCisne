package com.mdSync.model.comercial;

import javax.persistence.Transient;

public abstract class GenericEntity {
	
	public GenericEntity(){ }
	
	public String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public abstract Object getPrimaryKey();
}