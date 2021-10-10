package ru.kuznetsov.vikingwar;

import lombok.Getter;

public class GameController {
    @Getter
    private int currentDay;
    private IslandMap map;

    public GameController(MapReader mapReader) {
        map = mapReader.read();
        currentDay = 0;
    }

    public void start() {

    }
}
