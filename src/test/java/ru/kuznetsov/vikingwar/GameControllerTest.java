package ru.kuznetsov.vikingwar;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class GameControllerTest {

    private IslandMap islandMap;

    @BeforeAll
    public void createContext() {
        islandMap = new IslandMap();

        Island island1 = new Island("Остров1");
        Island island2 = new Island("Остров2");
        Island island3 = new Island("Остров3");
        Island island4 = new Island("Остров4");
        Island island5 = new Island("Остров5");
        Island island6 = new Island("Остров6");

        islandMap.addIsland(island1, Arrays.asList(Pair.of(Direction.NORTH, island3), Pair.of(Direction.SOUTH, island4),
                Pair.of(Direction.WEST, island2), Pair.of(Direction.EAST, island6)));
        islandMap.addIsland(island2, Arrays.asList(Pair.of(Direction.EAST, island1)));
        islandMap.addIsland(island3, Arrays.asList(Pair.of(Direction.SOUTH, island1)));
        islandMap.addIsland(island4,
                Arrays.asList(Pair.of(Direction.NORTH, island1), Pair.of(Direction.EAST, island5)));
        islandMap.addIsland(island5,
                Arrays.asList(Pair.of(Direction.NORTH, island6), Pair.of(Direction.WEST, island4)));
        islandMap.addIsland(island6,
                Arrays.asList(Pair.of(Direction.WEST, island1), Pair.of(Direction.SOUTH, island5)));
    }

    @Test
    public void distributeVikingsTest() {
    }

}