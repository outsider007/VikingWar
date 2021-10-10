package ru.kuznetsov.vikingwar;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameController {
    @Getter
    private int currentDay;
    private final IslandMap islandMap;

    public GameController(MapReader mapReader) {
        islandMap = mapReader.read();
        currentDay = 0;
    }

    public void start() {
        try {
            System.out.print("Enter the number of vikings: ");
            distributeVikings(new Scanner(System.in).nextInt());
        } catch (InputMismatchException exception) {
            System.out.println("Incorrect input format! Input value must be integer number.");
        }

        while (currentDay++ < 10000 || islandMap.isDeadLock()) {
            startBattlesToday();
        }

        System.out.println(islandMap.getActualMapScheme());
        System.out.println("");
    }

    private void startBattlesToday() {
        islandMap.getIslands()
                .stream()
                .filter(island -> !island.getVikingList().isEmpty())
                .map(island -> island.getVikingList().get(0))
                .collect(Collectors.toList())
                .forEach(viking -> {
                    List<Island> availableIslands = islandMap.getAvailableIslandsByIsland(viking.getCurrentIsland());
                    Collections.shuffle(availableIslands);
                    viking.swimToIsland(availableIslands.get(0));
                });

        islandMap.getIslands().forEach(island -> {
            List<Viking> vikingList = island.getVikingList();
            if (vikingList.size() == 1 && islandMap.getAvailableIslandsByIsland(island).isEmpty()) {
                Viking viking = vikingList.get(0);
                System.out.printf("АГР!!! %s застрял на %s и больше не участвует в войне%n", viking.getName(),
                        viking.getCurrentIsland().getName());
            } else if (vikingList.size() == 2) {
                island.startWar();
                System.out.printf("АГР!!! На %s уничтожен маяк, благодаря %s и %s%n", island.getName(),
                        vikingList.get(0).getName(), vikingList.get(1).getName());
                island.getVikingList().clear();
            }
        });
    }

    private void distributeVikings(int vikingsQuantity) {
        int freeVikingsQuantity = vikingsQuantity;
        ArrayList<Island> islands = new ArrayList<>(islandMap.getIslands());
        Collections.shuffle(islands);

        for (Island island : islands) {
            if (!island.isVikingLocated()) {
                freeVikingsQuantity--;
                island.locateViking(new Viking("Викинг" + (vikingsQuantity - freeVikingsQuantity), island));
                if (freeVikingsQuantity == 0) {
                    break;
                }
            }
        }

        if (freeVikingsQuantity > 0) {
            throw new IllegalArgumentException("Incorrect ratio of the number of vikings and islands! The number of " +
                    "vikings should not exceed the number of islands.");
        }
    }

}
