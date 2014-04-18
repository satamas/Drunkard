package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

/**
 * Created by atamas on 17.04.14.
 */
public abstract class MovableObject extends ActiveObject {

    MovableObject(Field field) {
        super(field);
    }

    protected void moveTo(Field field){
        if(!field.isEmpty()){
            throw new FieldIsNotEmptyException();
        } else {
            this.field.delObject();
            this.field = field;
            this.field.setObject(this);
        }
    }



    class FieldIsNotEmptyException extends RuntimeException {

    }
}
