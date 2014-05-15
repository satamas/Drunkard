package ru.spbau.mit.satamas;

import ru.spbau.mit.satamas.MapObjects.MapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


public class Field {

    static int fieldCounter = 0;
    public int fieldNo;
    private boolean bright = false;
    private List<Field> neighbours = new ArrayList<Field>(4);


    private MapObject obj;

    public Field(int noOfNeighbours) {
        for (int i = 0; i < noOfNeighbours; ++i) {
            neighbours.add(null);
        }
        fieldNo = fieldCounter;
        ++fieldCounter;
    }


    public MapObject getObj() {
        return obj;
    }

    public Boolean isEmpty() {
        if (obj == null) {
            return true;
        } else {
            return false;
        }
    }

    public void delObject() {
        this.obj = null;
    }

    public void setObject(MapObject obj) {
        this.obj = obj;
        obj.setField(this);
    }

    public void setNeighbour(Field field, int no) {
        neighbours.set( no, field );
    }

    public char repr() {
        if (obj == null) {
            if (bright) {
                return '*';
            } else {
                return '.';
            }
        } else {
            return obj.repr();
        }
    }

    public boolean isBright() {
        return bright;
    }

    public void setBright(boolean bright) {
        this.bright = bright;
    }

    public boolean isBorder() {
        for (Field l : neighbours) {
            if (l == null) {
                return true;
            }
        }
        return false;
    }

    public List<Field> getNeighbours() {
        return neighbours;
    }

    public Field getSingleNeighbour() {
        for( Field f : neighbours){
            if( f != null){
                return f;
            }
        }
        throw new NoSuchElementException("Neighbour do not exist");
    }

    public Field getRandomNeighbour(){
        Random rand = new Random();
        int randInt = rand.nextInt(neighbours.size());
        return neighbours.get(randInt);
    }
}

