package ru.spbau.mit.satamas;

import ru.spbau.mit.satamas.MapObjects.*;

import java.util.ArrayList;


public class Map {
	private static int SIZE_X = 15;
	private static int SIZE_Y = 15;
    private static int REPR_SIZE_X = SIZE_X + 2;
    private static int REPR_SIZE_Y = SIZE_Y + 2;

    private static char[][] mapRepr = new char[REPR_SIZE_X][REPR_SIZE_Y];
    private static Field entryPoint;
    private static ArrayList<MapObject> activeObjects = new ArrayList<MapObject>();
    private static ArrayList<MapObject> newObjects = new ArrayList<MapObject>();
    private static ArrayList<MapObject> garbage = new ArrayList<MapObject>();

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
        new PointForGlass(pointForGlassField);
        map[0 + SIZE_X * 4].setLeftNeighbour(pointForGlassField);
        activeObjects.add(pointForGlassField.getObj());

        Field policeStationField = new Field();
        policeStationField.setLeftNeighbour(map[14 + SIZE_X * 3]);
        new PoliceStation(policeStationField);
        map[14 + SIZE_X * 3].setRightNeighbour(policeStationField);
        activeObjects.add(policeStationField.getObj());

        Field innField = new Field();
        innField.setLowerNeighbour(map[9 + SIZE_X * 0]);
        new Inn(innField);
        map[9 + SIZE_X * 0].setUpperNeighbour(innField);
        activeObjects.add(innField.getObj());
        entryPoint = innField;
    }

    public static void step() {
        for (MapObject obj : activeObjects) {
            obj.nextStep();
        }

        for (MapObject obj : garbage) {
            activeObjects.remove(obj);
        }
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
        for (int j = 0; j <= SIZE_Y +1; ++j) {
            for (int i = 0; i <= SIZE_X +1; ++i) {
                System.out.print(mapRepr[i][j]);
            }
            System.out.println();
        }
    }

    public static int size() {
        return SIZE_X * SIZE_Y + 3;
    }

    public static void addActiveObject(MapObject obj) {
        newObjects.add(obj);
    }

}
