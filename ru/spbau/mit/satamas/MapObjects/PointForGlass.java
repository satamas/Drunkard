package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;

/**
 * Created by atamas on 16.04.14.
 */
public class PointForGlass  extends ActiveObject{
    public boolean hasBeggar = true;
    public int timer = 0;

    public PointForGlass(Field field) {
        super(field);
    }

    public void getInto(Beggar b){
        Map.killObj(b);
        b.field.delObject();
        timer = 30;
        hasBeggar = true;
    }


    @Override
    public char repr() {
        return 'R';
    }

    @Override
    public void nextStep() {
        if(hasBeggar) {
            if(timer == 0) {
                Field neighbour = field.getSingleNeighbour();
                if(neighbour.getObj() == null) {
                    hasBeggar = false;
                    Map.addActiveObject(new Beggar(neighbour));
                }
            } else{
                --timer;
            }
        }
    }
}
