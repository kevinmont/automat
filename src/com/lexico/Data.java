package com.lexico;

public class Data {
	
	private String type, var, value, state, typeValue;
	public Data(String type, String var, String value, String state, String typeValue) {
		this.type = type;
		this.var = var;
		this.value = value;
		this.state = state;
		this.typeValue = typeValue;
	}
	/**
	 * Type of the symbol. The symbols are Integer, String, Character, Float 
	 * @return <strong> String <strong> 
	 **/
	public String getType() {
		return type;
	}
	/**
	 *@param type set the value of type if this has been change
	 **/
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Name of the id
	 * @return String
	 **/
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public Data() {
	}
	
	
}
