package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

public class FallenDrunkie extends MapObject{

    FallenDrunkie(Field field) {
        super(field);
    }

    @Override
    public char repr() {
        return '&';
    }

	@Override
	public void nextStep() {
		// TODO Auto-generated method stub
		
	}

}
