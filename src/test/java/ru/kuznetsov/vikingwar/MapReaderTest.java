package ru.kuznetsov.vikingwar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapReaderTest {

    @Test
    public void testFileMapReader() {
        IslandMap readiedMap = new FileMapReaderImpl().read();

    }
}