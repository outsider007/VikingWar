package ru.kuznetsov.vikingwar;

public class VikingWarApplication {
    public static void main(String[] args) {
        new GameController(new FileMapReaderImpl()).start();
    }
}
