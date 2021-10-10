package ru.kuznetsov.vikingwar;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IslandMap {
    private final Map<Island, List<Pair<Direction, Island>>> map;

    public IslandMap() {
        map = new LinkedHashMap<>();
    }

    public void addIsland(Island island, List<Pair<Direction, Island>> islandDirectionList) {
        map.put(island, islandDirectionList);
    }

    public List<Island> getIslands() {
        return new ArrayList<>(map.keySet());
    }

    public List<Island> getAvailableIslandsByIsland(Island targetIsland) {
        return map.get(targetIsland)
                .stream()
                .map(Pair::getRight)
                .filter(Island::isFunctioningLightHouse)
                .collect(Collectors.toList());
    }

    public boolean isDeadLock() {
        return map.entrySet()
                .stream()
                .anyMatch(islandListEntry -> islandListEntry.getKey().isVikingLocated() && islandListEntry.getValue()
                        .stream()
                        .anyMatch(directionIslandPair -> directionIslandPair.getRight().isFunctioningLightHouse()));
    }

    public String getActualMapScheme() {
        StringBuilder actualScheme = new StringBuilder();
        map.entrySet().stream().filter(islandListEntry -> {
            Island rootIsland = islandListEntry.getKey();
            return rootIsland.isFunctioningLightHouse() && !getAvailableIslandsByIsland(rootIsland).isEmpty();
        }).forEach(islandListEntry -> {
            actualScheme.append(islandListEntry.getKey().getName());
            islandListEntry.getValue().forEach(directionIslandPair -> {
                actualScheme.append(String.format(" %s=%s", directionIslandPair.getLeft().getDirectionName(),
                        directionIslandPair.getRight().getName()));
            });
            actualScheme.append("\n");
        });

        return actualScheme.toString();
    }
}
