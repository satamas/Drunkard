package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;

/**
 * Created by atamas on 16.04.14.
 */
public class Inn extends MapObject{
    int timer = 0;

    public Inn(Field field) {
        super(field);
    }

    @Override
    public char repr() {
        return 'T';
    }

    @Override
    public void nextStep() {
        if(timer == 0) {
            Field neighbour = field.getNeighbours().get(2);
            if(neighbour.getObj() == null) {
                new Drunkard(neighbour);
                Map.addActiveObject(field.getNeighbours().get(2).getObj());
            }
            timer = 20;
        } else{
            --timer;
        }
    }
}
