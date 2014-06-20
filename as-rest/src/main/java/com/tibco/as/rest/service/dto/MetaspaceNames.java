package com.tibco.as.rest.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlRootElement(name = "metaspace")
public class MetaspaceNames extends BaseDto{
	@XmlElementWrapper(name="names")
    @XmlElement(name="name")
	private List<String> names;

	public MetaspaceNames(){
		
	}
	
	public MetaspaceNames(List<String> names){
		this.names = names;
	}
	
	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}	
}
