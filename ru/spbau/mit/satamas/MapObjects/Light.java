package ru.spbau.mit.satamas.MapObjects;

import ru.spbau.mit.satamas.Field;

/**
 * Created by atamas on 15.04.14.
 */
public class Light extends MapObject{

    public Light(Field field) {
        super(field);
        setBrightness(field, 0, 3);
    }


    private void setBrightness(Field field, int dist, int max_dist) {
        if (dist <= max_dist) {
            field.setBright(true);
            for (Field f : field.getNeighbours()) {
                if( f != null ) {
                    setBrightness(f, dist + 1, max_dist);
                }
            }
        }
    }


    @Override
    public char repr() {
        return 'L';
    }

}
