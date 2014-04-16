package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;

/**
 * Created by atamas on 16.04.14.
 */
public class PointForGlass  extends MapObject{
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
                Field neighbour = field.getNeighbours().get(1);
                if(neighbour.getObj() == null) {
                    new Beggar(neighbour);
                    hasBeggar = false;
                    Map.addActiveObject(field.getNeighbours().get(1).getObj());
                }
            } else{
                --timer;
            }
        }
    }
}
