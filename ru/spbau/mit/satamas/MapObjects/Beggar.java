package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;
import ru.spbau.mit.satamas.Map;
import ru.spbau.mit.satamas.WayFinder;

import java.util.List;


/**
 * Created by atamas on 16.04.14.
 */
public class Beggar extends MovableObject {
    public int stepNo = 0;
    boolean hasBottle = false;
    private int[] lastVisit = new int[Map.size()];

    public Beggar(Field field) {
        super(field);
    }


    @Override
    public char repr() {
        return 'z';
    }

    @Override
    public void nextStep() {
        if (!hasBottle) {
            Field step = chooseField();

            if (step.getObj() instanceof Bottle) {
                step.delObject();
                hasBottle = true;

            }
            moveTo(step);
            ++stepNo;
            lastVisit[field.fieldNo] = stepNo;
        } else {
            Field step = WayFinder.findWay(this.field, PointForGlass.class, false);
            if (step.getObj() == null) {
                moveTo(step);
            } else if (step.getObj() instanceof PointForGlass) {
                ((PointForGlass) step.getObj()).getInto(this);
            }
        }
    }

    private Field chooseField() {
        List<Field> neighbours = field.getNeighbours();

        for (Field f : neighbours) {
            if (f != null && f.getObj() instanceof Bottle) {
                return f;
            }
        }

        Field bestStep = field;
        int minLastVisit = lastVisit[field.fieldNo];
        for (Field f : neighbours) {
            if (f != null && minLastVisit > lastVisit[f.fieldNo] && f.getObj() == null && !f.isBorder()) {
                bestStep = f;
                minLastVisit = lastVisit[f.fieldNo];
            }
        }

        if (bestStep == null) {
            for (Field f : neighbours) {
                if (f != null && minLastVisit > lastVisit[f.fieldNo] && f.getObj() == null) {
                    bestStep = f;
                    minLastVisit = lastVisit[f.fieldNo];
                }
            }
        }

        return bestStep;
    }
}
