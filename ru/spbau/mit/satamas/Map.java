package ru.spbau.mit.satamas;
import java.util.ArrayList;

import ru.spbau.mit.satamas.MapObjects.Column;
import ru.spbau.mit.satamas.MapObjects.Drunkard;
import ru.spbau.mit.satamas.MapObjects.MapObject;


public class Map {
	private static int sizeX = 15;
	private static int sizeY = 15;
	private static Field[] map = new Field[sizeX * sizeY];
	private static ArrayList<MapObject> objects = new ArrayList<MapObject>();
	private static ArrayList<MapObject> garbage = new ArrayList<MapObject>();
	private static int stepNo = 0;
	
	public static void init(){
		for(int i = 0; i < sizeY; i++){
			for(int j = 0; j < sizeX; j++){
				map[j+sizeX*i] = new Field();
			}
		}
		Column c = new Column();
		map[7+sizeX*7].setObject(c);
	}
	
	public static void step(){
		for(MapObject obj : objects){
			obj.nextStep();
		}
		
		for(MapObject obj : garbage){
			objects.remove(obj);
		}
		garbage.clear();
		if(stepNo%20 == 0){
			if(map[9].isEmpty()){
				Drunkard d = new Drunkard(9, 0);
				objects.add(d);
				map[9].setObject(d);
			}
		}
		Map.print();
		stepNo++;
	}
	
	public static void killObj(MapObject obj){
		garbage.add(obj);
	}
	
	public static void print(){
		System.out.println("         T     ");
		for(int i = 0; i < sizeY; i++){
			for(int j = 0; j < sizeX; j++){
				map[j+sizeX*i].print();
			}
			System.out.println("");
		}
	}
	
	public static Field getField(int x, int y){
		if( x < 0 || x>=15 || y <0 || y>=15 ){
			return null;
		}
		return map[x+y*sizeX];
			
	}

}
