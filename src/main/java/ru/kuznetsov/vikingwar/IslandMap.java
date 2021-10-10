package ru.kuznetsov.vikingwar;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IslandMap {
    private final Map<Island, List<Pair<Direction, Island>>> map;

    public IslandMap() {
        map = new LinkedHashMap<>();
    }

    public void addIsland(Island island, List<Pair<Direction, Island>> islandDirectionList) {
        map.put(island, islandDirectionList);
    }
}
