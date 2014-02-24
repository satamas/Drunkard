package ru.spbau.mit.satamas.MapObjects;
import java.util.Random;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;


public class Drunkard implements MapObject{
	int x;
	int y;
	Boolean haveBottle = true;
	
	public Drunkard(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void nextStep(){
		Random rand = new Random();
		int randInt = rand.nextInt(4);
		Field destination = null;
		
		switch (randInt){
		
		case 0:
			destination = Map.getField(x-1, y);
		break;
		
		case 1:
			destination = Map.getField(x, y-1);
		break;
		
		case 2:
			destination = Map.getField(x+1, y);
		break;
		
		case 3:
			destination = Map.getField(x, y+1);
		break;

		}
		
		if(destination != null){
			MapObject object = destination.getObj();
			if(object == null){
				Map.getField(x, y).delObject();
				destination.setObject(this);
				int prob = rand.nextInt(30);
				if ( prob < 1 && haveBottle == true){
					Bottle b = new Bottle();
					Map.getField(x, y).setObject(b);
					haveBottle = false;
				}
				
				switch(randInt){
				case 0:
					x--;
				break;
				case 1:
					y--;
				break;
				case 2:
					x++;
				break;
				case 3:
					y++;
				break;
				}
			} else{
				if(object instanceof Column || object instanceof SleepingDrunkie){
					Map.getField(x, y).delObject();
					SleepingDrunkie Z = new SleepingDrunkie();
					Map.getField(x, y).setObject(Z);
					Map.killObj(this);
				}
				if(object instanceof Bottle){
					Map.getField(x, y).delObject();
					FallenDrunkie F = new FallenDrunkie();
					Map.getField(x, y).setObject(F);
					Map.killObj(this);						
				}
			}
		}
		
	}
	
	public void print(){
		System.out.print("D");
	}
}
