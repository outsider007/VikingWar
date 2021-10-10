package ru.kuznetsov.vikingwar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileMapReaderImpl implements MapReader {
    private String pathName;

    public FileMapReaderImpl() {
        pathName = Objects.requireNonNull(getClass().getClassLoader().getResource("IslandMap.txt")).getFile();
    }

    public FileMapReaderImpl(String pathName) {
        this.pathName = pathName;
    }

    @Override
    public IslandMap read() {
        IslandMap islandMap = new IslandMap();

        try {
            fillIslandMap(islandMap,
                    createIslandCoordsMap(FileUtils.readLines(new File(pathName), Charset.defaultCharset())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return islandMap;
    }

    private Map<Island, List<String>> createIslandCoordsMap(List<String> fileLines) {
        Map<Island, List<String>> islandCoordsMap = new LinkedHashMap<>();

        fileLines.forEach(fileLine -> {
            List<String> lineItems = Arrays.stream(fileLine.split(" "))
                    .collect(Collectors.toCollection(LinkedList::new));
            islandCoordsMap.put(new Island(lineItems.remove(0)), lineItems);
        });

        return islandCoordsMap;
    }

    private void fillIslandMap(IslandMap islandMap, Map<Island, List<String>> islandCoordsMap) {
        islandCoordsMap.forEach((island, coords) -> islandMap.addIsland(island, coords.stream().map(coordsItem -> {
            String[] split = coordsItem.split("=");
            return Pair.of(Direction.getByName(split[0]),
                    islandCoordsMap.keySet()
                            .stream()
                            .filter(islandKey -> islandKey.getName().equalsIgnoreCase(split[1]))
                            .findFirst()
                            .orElseThrow(IllegalArgumentException::new));
        }).collect(Collectors.toList())));
    }

}
