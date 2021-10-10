package ru.kuznetsov.vikingwar;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Island {
    private String name;
    private LightHouse lightHouse;
    private List<Viking> vikingList;

    public Island(String name) {
        this.name = name;
        lightHouse = new LightHouse();
        vikingList = new ArrayList<>();
    }
}
