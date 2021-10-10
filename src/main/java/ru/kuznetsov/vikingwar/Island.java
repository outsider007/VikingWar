package ru.kuznetsov.vikingwar;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Island {
    @Getter
    private String name;
    private LightHouse lightHouse;
    @Getter
    private List<Viking> vikingList;

    public Island(String name) {
        this.name = name;
        lightHouse = new LightHouse();
        vikingList = new ArrayList<>();
    }

    public void locateViking(Viking viking) {
        vikingList.add(viking);
        if (vikingList.size() > 1) {

            lightHouse.setCurrentState(LightHouseState.DESTROYED);
        }
    }

    public void relocateViking(Viking viking) {
        vikingList.remove(viking);
    }

    public boolean isVikingLocated() {
        return !vikingList.isEmpty();
    }

    public boolean isFunctioningLightHouse() {
        return lightHouse.getCurrentState().equals(LightHouseState.FUNCTIONING);
    }

    public void startWar() {
        lightHouse.setCurrentState(LightHouseState.DESTROYED);
    }
}
