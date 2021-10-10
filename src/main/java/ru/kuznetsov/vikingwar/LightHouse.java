package ru.kuznetsov.vikingwar;

import lombok.Data;

@Data
public class LightHouse {
    private LightHouseState currentState;

    public LightHouse() {
        this.currentState = LightHouseState.FUNCTIONING;
    }
}
