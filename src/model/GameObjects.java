package model;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    private List<Wall> walls;
    private List<Box> boxes;
    private List<Home> homes;

    private Player player;

    public GameObjects(List<Wall> walls, List<Box> boxes, List<Home> homes, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.homes = homes;
        this.player = player;
    }

    List<Wall> getWalls() {
        return walls;
    }

    List<Box> getBoxes() {
        return boxes;
    }

    void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    List<Home> getHomes() {
        return homes;
    }

    public Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }

    public List<GameObject> getAll() {
        List<GameObject> gameObjects = new ArrayList<>();

        gameObjects.addAll(walls);
        gameObjects.addAll(homes);
        gameObjects.addAll(boxes);
        gameObjects.add(player);

        return gameObjects;
    }
}
