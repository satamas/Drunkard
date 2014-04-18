package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

public abstract class MapObject {
    protected Field field = null;
    public abstract char repr();

    MapObject(Field field){
        this.field = field;
        field.setObject(this);
    }

    public void setField(Field field) {
        this.field = field;
    }
}
