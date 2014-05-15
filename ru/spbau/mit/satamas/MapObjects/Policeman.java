package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;
import ru.spbau.mit.satamas.WayFinder;

/**
 * Created by atamas on 15.04.14.
 */
public class Policeman extends MovableObject {
    private boolean hasDrunkard = false;

    Policeman(Field field) {
        super(field);
    }

    @Override
    public char repr() {
        return 'P';
    }

    @Override
    public void nextStep() {
        if (!hasDrunkard) {
            Field step = WayFinder.findWay(field, FallenDrunkie.class, true);

            if (step == field) {
                step = WayFinder.findWay(field, SleepingDrunkie.class, true);
            }

            if (step.getObj() instanceof SleepingDrunkie || step.getObj() instanceof FallenDrunkie) {
                Map.killObj(step.getObj());
                step.delObject();
                hasDrunkard = true;
            }

            moveTo(step);
        } else {
            Field step = WayFinder.findWay(field, PoliceStation.class, false);
            if (step.getObj() == null) {
                moveTo(step);
            } else if (step.getObj() instanceof PoliceStation) {
                ((PoliceStation) step.getObj()).getInto(this);
            }
        }
    }

}
