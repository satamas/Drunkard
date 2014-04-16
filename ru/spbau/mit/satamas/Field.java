package ru.spbau.mit.satamas;

import ru.spbau.mit.satamas.MapObjects.MapObject;

import java.util.ArrayList;
import java.util.List;


public class Field {

    static int fieldCounter = 0;
    public int fieldNo;
    private boolean bright = false;
    private List<Field> neighbours = new ArrayList<Field>(4);


    private MapObject obj;

    public Field() {
        for (int i = 0; i < 4; ++i) {
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

    public void setUpperNeighbour(Field field) {
        neighbours.set(0, field);
    }

    public void setRightNeighbour(Field field) {
        neighbours.set(1, field);
    }

    public void setLowerNeighbour(Field field) {
        neighbours.set(2, field);
    }

    public void setLeftNeighbour(Field field) {
        neighbours.set(3, field);
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
}

