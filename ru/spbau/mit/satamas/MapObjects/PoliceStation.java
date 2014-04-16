package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;
import ru.spbau.mit.satamas.WayFinder;

/**
 * Created by atamas on 16.04.14.
 */
public class PoliceStation extends MapObject {
    public boolean hasPoliceman = true;

    public PoliceStation(Field field) {
        super(field);
    }


    @Override
    public char repr() {
        return 'S';
    }

    @Override
    public void nextStep() {
        if(hasPoliceman) {
            Field step = WayFinder.findWay(field, FallenDrunkie.class, true);
            if (step == field) {
                step = WayFinder.findWay(field, SleepingDrunkie.class, true);
            }
            if (step != field && step.getObj() == null) {
                Field neighbour = field.getNeighbours().get(3);
                if(neighbour.getObj() == null) {
                    new Policeman(neighbour);
                    hasPoliceman = false;
                    Map.addActiveObject(field.getNeighbours().get(3).getObj());
                }
            }
        }
    }
}