package ru.spbau.mit.satamas;

import ru.spbau.mit.satamas.MapObjects.MapObject;

public class Field {
	
	private MapObject obj;
	
	public Field(){
	
	}
	
	public MapObject getObj(){
		return obj;
	}
	
	public Boolean isEmpty(){
		if(obj == null){
			return true;
		} else{
			return false;
		}
	}
	
	public void delObject(){
		this.obj = null;
	}
	
	public void setObject(MapObject obj){
		this.obj = obj;
	}
	
	public void print(){
		if(obj == null){
			System.out.print(".");
		} else{
			obj.print();
		}
	}
}
