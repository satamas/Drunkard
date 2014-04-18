package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

/**
 * Created by atamas on 17.04.14.
 */
public abstract class ActiveObject extends MapObject {

    ActiveObject(Field field) {
        super(field);
    }

    public abstract void nextStep();
}