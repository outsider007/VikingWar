package ru.kuznetsov.vikingwar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Viking {
    private String name;
    private Island currentIsland;
}
