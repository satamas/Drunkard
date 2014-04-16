package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

public class Column extends MapObject {
    public Column(Field field) {
        super(field);
    }

    @Override
    public char repr() {
        return 'C';
    }

    public void nextStep(){
	}

}
