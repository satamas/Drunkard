package ru.spbau.mit.satamas.MapObjects;
import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;

import java.util.Random;


public class Drunkard extends MovableObject{
	Boolean haveBottle = true;
	
	public Drunkard(Field field){
        super(field);
	}

    @Override
    public char repr() {
        return 'D';
    }

    @Override
    public void nextStep(){
		Random rand = new Random();
		int randInt = rand.nextInt(5);
        Field destination = null;
        if(randInt<4){
            destination = field.getNeighbours().get(randInt);
        }
		
		if(destination != null){
			MapObject object = destination.getObj();
			if(object == null){
				field.delObject();
				int prob = rand.nextInt(30);
				if ( prob < 1 && haveBottle == true){
					new Bottle(field);
					haveBottle = false;
				}
                destination.setObject(this);
			} else{
                if(object instanceof Column || object instanceof SleepingDrunkie){
                    field.delObject();
                    new SleepingDrunkie(field);
                    Map.killObj(this);
                }
                if(object instanceof Bottle){
                    field.delObject();
                    new FallenDrunkie(field);
                    Map.killObj(this);
                }
            }


		}
		
	}
}
