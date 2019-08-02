package model;

import controller.EventListener;

import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Model {
    static final int FIELD_CELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 5;
    private final LevelLoader levelLoader = new LevelLoader(Paths
            .get("./src/" + getClass().getPackage().getName().replace('.', '/') + "./levels.txt"));
    private Deque<List<Box>> previousBoxes = new ArrayDeque<>();
    private Deque<Player> previousPlayer = new ArrayDeque<>();
    private Deque<Integer> previousMoves = new ArrayDeque<>(), previousPushes = new ArrayDeque<>();

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void startLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void movePlayer(Direction direction) {
        Player player = gameObjects.getPlayer();
        List<Box> boxes = gameObjects.getBoxes();

        if (checkWallCollision(player, direction, 1, 1)) return;

        boolean isFirstCollision = checkWithFirstBoxCollision(direction);

        if (isFirstCollision)
            if (checkWithSecondBoxOrSecondWallCollision(direction)) return;

        saveState();

        if (isFirstCollision)
            moveObject(boxes.get(boxes.indexOf(new Box(player.getX() + player.getDeltaXRelativePlayer(direction),
                    player.getY() + player.getDeltaYRelativePlayer(direction)))), direction);

        moveObject(player, direction);

        checkCompletion();
    }

    private boolean checkWallCollision(Player player, Direction direction, int shiftDistanceX, int shiftDistanceY) {
        return player.isCollision(gameObjects.getWalls(), Wall.class, direction, shiftDistanceX, shiftDistanceY);
    }

    private boolean checkWithFirstBoxCollision(Direction direction) {
        return gameObjects.getPlayer().isCollision(gameObjects.getBoxes(), Box.class, direction, 1, 1);
    }

    private boolean checkWithSecondBoxOrSecondWallCollision(Direction direction) {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction, 2, 2)) return true;
        return player.isCollision(gameObjects.getBoxes(), Box.class, direction, 2, 2);
    }

    private void moveObject(Movable movable, Direction direction) {
        Player player = gameObjects.getPlayer();
        movable.move(player.getDeltaXRelativePlayer(direction), player.getDeltaYRelativePlayer(direction));
    }

    private void checkCompletion() {
        List<Home> homeList = gameObjects.getHomes();
        List<Box> boxList = gameObjects.getBoxes();

        for (Home home : homeList)
            if (!boxList.contains(new Box(home.getX(), home.getY()))) return;

        eventListener.levelCompleted(currentLevel);
    }

    private void saveState() {
        List<Box> boxes = new ArrayList<>();
        Player player = gameObjects.getPlayer();

        for (Box box : gameObjects.getBoxes()) {
            boxes.add(new Box(box.getX(), box.getY()));
        }

        previousBoxes.push(boxes);
        previousPlayer.push(new Player(player.getX(), player.getY()));
        previousMoves.push(player.getMoves());
        previousPushes.push(Box.getPushes());
    }

    public void rollBack() {
        if (previousMoves.size() != 0) {
            gameObjects.setBoxes(previousBoxes.pop());
            gameObjects.setPlayer(previousPlayer.pop());
            gameObjects.getPlayer().setMoves(previousMoves.pop());
            Box.setPushes(previousPushes.pop());
        }
    }
}
