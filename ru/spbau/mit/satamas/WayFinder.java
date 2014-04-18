package ru.spbau.mit.satamas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atamas on 16.04.14.
 */
public class WayFinder {

    public static Field findWay(Field pos, Class destination, boolean onlyVisible) {
        List<Field> bfsList = new ArrayList<Field>();

        List<Boolean> visited = new ArrayList<Boolean>();
        List<Integer> bfsPrev = new ArrayList<Integer>();
        for (int i = 0; i < Map.size(); ++i) {
            visited.add(false);
        }

        for (Field neighbour : pos.getNeighbours()) {
            if (neighbour != null) {
                visited.set(neighbour.fieldNo, true);
                bfsPrev.add(-1);
                bfsList.add(neighbour);
            }
        }

        int bfsIndex = 0;

        while (bfsIndex < bfsList.size()) {
            Field f = bfsList.get(bfsIndex);

            if (f.getObj() != null) {
                if (destination.isInstance(f.getObj())) {
                    if (!onlyVisible || f.isBright()) {
                        break;
                    } else {
                        ++bfsIndex;
                    }
                } else {
                    ++bfsIndex;
                }
            } else {
                for (Field neighbour : f.getNeighbours()) {
                    if (neighbour != null && !visited.get(neighbour.fieldNo)) {
                        visited.set(neighbour.fieldNo, true);
                        bfsList.add(neighbour);
                        bfsPrev.add(bfsIndex);
                    }
                }
                ++bfsIndex;
            }
        }

        if (bfsIndex < bfsList.size()) {
            int step = bfsIndex;

            while (bfsPrev.get(step) != -1) {
                step = bfsPrev.get(step);
            }
            return bfsList.get(step);
        } else {
            return pos;
        }
    }

}
