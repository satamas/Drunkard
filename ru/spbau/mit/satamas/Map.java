package ru.spbau.mit.satamas;

import ru.spbau.mit.satamas.MapObjects.*;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private static int SIZE_X = 15;
    private static int REPR_SIZE_X = SIZE_X + 2;
    private static int SIZE_Y = 15;
    private static int REPR_SIZE_Y = SIZE_Y + 2;
    private static char[][] mapRepr;
    private static int NO_OF_NEIGHBOURS;
    private static Field entryPoint;
    private static List<ActiveObject> activeObjects = new ArrayList<ActiveObject>();
    private static List<ActiveObject> newObjects = new ArrayList<ActiveObject>();
    private static List<MapObject> garbage = new ArrayList<MapObject>();
    private static MapType type;

    public static void init(MapType T) {
        type = T;
        if (T.equals(MapType.Normal)) {
            NO_OF_NEIGHBOURS = 4;
            mapRepr = new char[REPR_SIZE_X][REPR_SIZE_Y];
        } else {
            NO_OF_NEIGHBOURS = 6;
            REPR_SIZE_X *= 2;
            mapRepr = new char[REPR_SIZE_X][REPR_SIZE_Y];
        }

        Field[] map = new Field[SIZE_X * SIZE_Y];
        for(int i=0; i < mapRepr.length; ++i){
            for(int j =0; j <mapRepr[i].length; ++j){
                mapRepr[i][j] = ' ';
            }
        }

        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                map[j + SIZE_X * i] = new Field(NO_OF_NEIGHBOURS);
            }
        }

        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (T.equals(MapType.Normal)) {
                    map[j + SIZE_X * i].setNeighbour(j - 1 >= 0 ? map[j - 1 + SIZE_X * i] : null, 0);
                    map[j + SIZE_X * i].setNeighbour(i - 1 >= 0 ? map[j + SIZE_X * (i - 1)] : null, 1);
                    map[j + SIZE_X * i].setNeighbour(j + 1 < SIZE_X ? map[j + 1 + SIZE_X * i] : null, 2);
                    map[j + SIZE_X * i].setNeighbour(i + 1 < SIZE_Y ? map[j + SIZE_X * (i + 1)] : null, 3);
                } else {
                    if (i % 2 == 0) {
                        map[j + SIZE_X * i].setNeighbour(j - 1 >= 0 ? map[j - 1 + SIZE_X * i] : null, 0);
                        map[j + SIZE_X * i].setNeighbour(i - 1 >= 0 ? map[j + SIZE_X * (i - 1)] : null, 1);
                        map[j + SIZE_X * i].setNeighbour((i - 1 >= 0 && j + 1 < SIZE_X) ? map[(j + 1) + SIZE_X * (i - 1)] : null, 2);
                        map[j + SIZE_X * i].setNeighbour(j + 1 < SIZE_X ? map[j + 1 + SIZE_X * i] : null, 3);
                        map[j + SIZE_X * i].setNeighbour((i + 1 < SIZE_Y && j + 1 < SIZE_X) ? map[(j + 1) + SIZE_X * (i + 1)] : null, 4);
                        map[j + SIZE_X * i].setNeighbour(i + 1 < SIZE_Y ? map[j + SIZE_X * (i + 1)] : null, 5);
                    } else {
                        map[j + SIZE_X * i].setNeighbour(j - 1 >= 0 ? map[j - 1 + SIZE_X * i] : null, 0);
                        map[j + SIZE_X * i].setNeighbour((i - 1 >= 0 && j - 1 >= 0) ? map[(j - 1) + SIZE_X * (i - 1)] : null, 1);
                        map[j + SIZE_X * i].setNeighbour(i - 1 >= 0 ? map[j + SIZE_X * (i - 1)] : null, 2);
                        map[j + SIZE_X * i].setNeighbour(j + 1 < SIZE_X ? map[j + 1 + SIZE_X * i] : null, 3);
                        map[j + SIZE_X * i].setNeighbour(i + 1 < SIZE_Y ? map[j + SIZE_X * (i + 1)] : null, 4);
                        map[j + SIZE_X * i].setNeighbour((i + 1 < SIZE_Y && j - 1 >= 0) ? map[(j - 1) + SIZE_X * (i + 1)] : null, 5);
                    }
                }
            }
        }

        new Column(map[7 + SIZE_X * 7]);
        new Light(map[10 + SIZE_X * 3]);

        if (type.equals(MapType.Normal)) {
            Field pointForGlassField = new Field(NO_OF_NEIGHBOURS);
            pointForGlassField.setNeighbour(map[0 + SIZE_X * 4], 2);
            map[0 + SIZE_X * 4].setNeighbour(pointForGlassField, 0);
            activeObjects.add(new PointForGlass(pointForGlassField));


            Field policeStationField = new Field(NO_OF_NEIGHBOURS);
            policeStationField.setNeighbour(map[14 + SIZE_X * 3], 0);
            map[14 + SIZE_X * 3].setNeighbour(policeStationField, 2);
            activeObjects.add(new PoliceStation(policeStationField));


            Field innField = new Field(NO_OF_NEIGHBOURS);
            innField.setNeighbour(map[9 + SIZE_X * 0], 3);
            map[9 + SIZE_X * 0].setNeighbour(innField, 1);
            activeObjects.add(new Inn(innField));


            entryPoint = pointForGlassField;
        } else {
            Field pointForGlassField = new Field(NO_OF_NEIGHBOURS);
            pointForGlassField.setNeighbour(map[0 + SIZE_X * 4], 3);
            map[0 + SIZE_X * 4].setNeighbour(pointForGlassField, 0);
            activeObjects.add(new PointForGlass(pointForGlassField));


            Field policeStationField = new Field(NO_OF_NEIGHBOURS);
            policeStationField.setNeighbour(map[14 + SIZE_X * 3], 0);
            map[14 + SIZE_X * 3].setNeighbour(policeStationField, 3);
            activeObjects.add(new PoliceStation(policeStationField));


            Field innField = new Field(NO_OF_NEIGHBOURS);
            innField.setNeighbour(map[9 + SIZE_X * 0], 4);
            map[9 + SIZE_X * 0].setNeighbour(innField, 1);
            activeObjects.add(new Inn(innField));
            entryPoint = pointForGlassField;
        }


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

            if (type.equals(MapType.Normal)) {
                Field leftNeighbour = field.getNeighbours().get(0);
                Field upperNeighbour = field.getNeighbours().get(1);
                Field rightNeighbour = field.getNeighbours().get(2);
                Field downNeighbour = field.getNeighbours().get(3);
                if (leftNeighbour != null) {
                    updateRepr(leftNeighbour, x - 1, y, updated);
                }
                if (upperNeighbour != null) {
                    updateRepr(upperNeighbour, x, y - 1, updated);
                }
                if (rightNeighbour != null) {
                    updateRepr(rightNeighbour, x + 1, y, updated);
                }
                if (downNeighbour != null) {
                    updateRepr(downNeighbour, x, y + 1, updated);
                }

            } else {
                Field neighbour = field.getNeighbours().get(0);
                if (neighbour != null) {
                    updateRepr(neighbour, x - 2, y, updated);
                }

                neighbour = field.getNeighbours().get(1);
                if (neighbour != null) {
                    updateRepr(neighbour, x - 1, y - 1, updated);
                }

                neighbour = field.getNeighbours().get(2);
                if (neighbour != null) {
                    updateRepr(neighbour, x + 1, y - 1, updated);
                }

                neighbour = field.getNeighbours().get(3);
                if (neighbour != null) {
                    updateRepr(neighbour, x + 2, y, updated);
                }

                neighbour = field.getNeighbours().get(4);
                if (neighbour != null) {
                    updateRepr(neighbour, x + 1, y + 1, updated);
                }

                neighbour = field.getNeighbours().get(5);
                if (neighbour != null) {
                    updateRepr(neighbour, x - 1, y + 1, updated);
                }
            }
        }
    }

    public static void print() {
        updateRepr(entryPoint, 0, 5, new boolean[size()]);
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

    public enum MapType {
        Normal,
        Hexagonal
    }

}
