package com.mazmy.domainvalue;

public enum ConvertibleValue
{
	YES(true),NO(false);
	
	private final boolean value;
	
	ConvertibleValue(boolean value) {
		this.value = value;
	}
	
	public boolean value(){
		return value;
	}
}
