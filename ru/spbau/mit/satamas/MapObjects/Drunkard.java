package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;

import java.util.Random;


public class Drunkard extends MovableObject{
    private static final int DROP_BOTTLE_PROBABILITY = 30;
	private Boolean haveBottle = true;
	Random rand = new Random();

	public Drunkard(Field field){
        super(field);
	}

    @Override
    public char repr() {
        return 'D';
    }

    @Override
    public void nextStep(){
        Field destination = field.getRandomNeighbour();

        if(destination != null){
			MapObject object = destination.getObj();
			if(object == null){
				field.delObject();
				int prob = rand.nextInt(DROP_BOTTLE_PROBABILITY);
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
