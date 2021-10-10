package ru.kuznetsov.vikingwar;

import lombok.Getter;

import java.util.Arrays;

public enum Direction {
    NORTH("север"), SOUTH("юг"), WEST("запад"), EAST("восток");

    @Getter
    private String directionName;

    Direction(String directionName) {
        this.directionName = directionName;
    }

    public static Direction getByName(String name) {
        return Arrays.stream(values())
                .filter(direction -> direction.getDirectionName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
