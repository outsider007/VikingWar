package ru.kuznetsov.vikingwar;

public class VikingWarApplication {
    public static void main(String[] args) {

        GameController gameController = new GameController(new FileMapReaderImpl());
    }
}
