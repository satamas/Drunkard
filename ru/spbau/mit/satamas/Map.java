package ru.spbau.mit.satamas;

import ru.spbau.mit.satamas.MapObjects.*;

import java.util.ArrayList;
import java.util.List;


public class Map {
	private static int SIZE_X = 15;
    private static int REPR_SIZE_X = SIZE_X + 2;
    private static int SIZE_Y = 15;
    private static int REPR_SIZE_Y = SIZE_Y + 2;

    private static char[][] mapRepr = new char[REPR_SIZE_X][REPR_SIZE_Y];
    private static Field entryPoint;
    private static List<ActiveObject> activeObjects = new ArrayList<ActiveObject>();
    private static List<ActiveObject> newObjects = new ArrayList<ActiveObject>();
    private static List<MapObject> garbage = new ArrayList<MapObject>();

    public static void init() {
        Field[] map = new Field[SIZE_X * SIZE_Y];
        for(int i=0; i < mapRepr.length; ++i){
            for(int j =0; j <mapRepr[i].length; ++j){
                mapRepr[i][j] = ' ';
            }
        }

        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                map[j + SIZE_X * i] = new Field();
            }
        }

        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                map[j + SIZE_X * i].setLeftNeighbour(j - 1 >= 0 ? map[j - 1 + SIZE_X * i] : null);
                map[j + SIZE_X * i].setUpperNeighbour(i - 1 >= 0 ? map[j + SIZE_X * (i - 1)] : null);
                map[j + SIZE_X * i].setRightNeighbour(j + 1 < SIZE_X ? map[j + 1 + SIZE_X * i] : null);
                map[j + SIZE_X * i].setLowerNeighbour(i + 1 < SIZE_Y ? map[j + SIZE_X * (i + 1)] : null);
            }
        }

        new Column(map[7 + SIZE_X * 7]);
        new Light(map[10 + SIZE_X * 3]);

        Field pointForGlassField = new Field();
        pointForGlassField.setRightNeighbour(map[0 + SIZE_X * 4]);
        map[0 + SIZE_X * 4].setLeftNeighbour(pointForGlassField);
        activeObjects.add(new PointForGlass(pointForGlassField));


        Field policeStationField = new Field();
        policeStationField.setLeftNeighbour(map[14 + SIZE_X * 3]);
        map[14 + SIZE_X * 3].setRightNeighbour(policeStationField);
        activeObjects.add(new PoliceStation(policeStationField));


        Field innField = new Field();
        innField.setLowerNeighbour(map[9 + SIZE_X * 0]);
        map[9 + SIZE_X * 0].setUpperNeighbour(innField);
        activeObjects.add(new Inn(innField));


        entryPoint = innField;
    }

    public static void step() {
        for (ActiveObject obj : activeObjects) {
            obj.nextStep();
        }

        activeObjects.removeAll(garbage);
        garbage.clear();

        activeObjects.addAll(newObjects);
        newObjects.clear();

        Map.print();
    }

    public static void killObj(MapObject obj) {
        garbage.add(obj);
    }

    public static void updateRepr(Field field, int x, int y, boolean[] updated) {
        if (!updated[field.fieldNo]) {
            updated[field.fieldNo] = true;
            mapRepr[x][y] = field.repr();
            Field upperNeighbour = field.getNeighbours().get(0);
            Field rightNeighbour = field.getNeighbours().get(1);
            Field downNeighbour = field.getNeighbours().get(2);
            Field leftNeighbour = field.getNeighbours().get(3);
            if (upperNeighbour != null) {
                updateRepr(upperNeighbour, x, y - 1, updated);
            }
            if (rightNeighbour != null) {
                updateRepr(rightNeighbour, x + 1, y, updated);
            }
            if (downNeighbour != null) {
                updateRepr(downNeighbour, x, y + 1, updated);
            }
            if (leftNeighbour != null) {
                updateRepr(leftNeighbour, x - 1, y, updated);
            }
        }
    }

    public static void print() {
        updateRepr(entryPoint, 10, 0, new boolean[size()]);
        for (int j = 0; j < REPR_SIZE_Y; ++j) {
            for (int i = 0; i < REPR_SIZE_X; ++i) {
                System.out.print(mapRepr[i][j]);
            }
            System.out.println();
        }
    }

    public static int size() {
        return SIZE_X * SIZE_Y + 3;
    }

    public static void addActiveObject(ActiveObject obj) {
        newObjects.add(obj);
    }

}
