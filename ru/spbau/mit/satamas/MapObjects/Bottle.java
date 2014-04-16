package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

public class Bottle extends MapObject {

    Bottle(Field field) {
        super(field);
    }

    @Override
    public char repr() {
        return 'B';
    }

    @Override
	public void nextStep() {
		// TODO Auto-generated method stub
		
	}
	
}
