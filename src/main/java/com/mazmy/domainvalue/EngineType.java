package com.mazmy.domainvalue;

public enum EngineType
{
	ELECTRIC("electric"), GAS("gas"), PETROL("petrol"); 
	
	private final String type; 
	EngineType(String type) {
		this.type = type; 
	}
	
	public String type(){
		return type; 
	}
}
