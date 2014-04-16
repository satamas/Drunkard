package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

public class SleepingDrunkie extends MapObject {
    SleepingDrunkie(Field field) {
        super(field);
    }

    public void nextStep(){
	}

    @Override
    public char repr() {
        return 'Z';
    }

}
