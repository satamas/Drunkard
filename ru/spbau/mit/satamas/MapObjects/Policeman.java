package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;
import ru.spbau.mit.satamas.WayFinder;

/**
 * Created by atamas on 15.04.14.
 */
public class Policeman extends MapObject {
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

            field.delObject();
            field = step;
            field.setObject(this);
        } else {
            Field step = WayFinder.findWay(field, PoliceStation.class, false);
            if (step.getObj() == null) {
                field.delObject();
                field = step;
                field.setObject(this);
            } else if (step.getObj() instanceof PoliceStation) {
                ((PoliceStation) step.getObj()).hasPoliceman = true;
                Map.killObj(this);
                field.delObject();
            }
        }
    }

}
