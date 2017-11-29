package com.lexico;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Table {
	private LinkedHashMap<String, Data> table;
	
	public Table() {
		table= new LinkedHashMap<>();
	}
	
	public Data put(Data dat) {
		this.table.put(dat.getValue(), dat);
		return dat;
	}
	
	public Data find(String key) {
		return table.get(key);
	}

	@Override
	public String toString() {
		@SuppressWarnings("rawtypes")
		Iterator iterator= table.keySet().iterator();
		String cad="";
		while(iterator.hasNext()) {
			Data s= (Data) iterator.next();
			cad+="Type: "+s.getType()+ "\tNameVar: "+s.getVar()+"\tValue: "+s.getValue()+"\n"+
			"\tState: "+s.getState()+"\tType of Value: "+s.getTypeValue()+"\n";
		}
		return cad;
	}
	
	
}
