package model;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Player extends GameObject implements Movable {
    private int moves;

    Player(int x, int y) {
        super(x, y);
    }

    public int getMoves() {
        return moves;
    }

    void setMoves(int moves) {
        this.moves = moves;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillOval(getX() - Model.FIELD_CELL_SIZE / 2, getY() - Model.FIELD_CELL_SIZE / 2, getWidth(), getHeight());
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
        moves++;
    }

    int getDeltaXRelativePlayer(Direction direction) {
        int deltaX = 0;

        if (direction == Direction.LEFT) deltaX = -Model.FIELD_CELL_SIZE;
        else if (direction == Direction.RIGHT) deltaX = Model.FIELD_CELL_SIZE;

        return deltaX;
    }

    int getDeltaYRelativePlayer(Direction direction) {
        int deltaY = 0;

        if (direction == Direction.UP) deltaY = -Model.FIELD_CELL_SIZE;
        else if (direction == Direction.DOWN) deltaY = Model.FIELD_CELL_SIZE;

        return deltaY;
    }

    <T extends Collisionable> boolean isCollision(List<T> objectSet, Class<T> classOfSetParameter, Direction direction, int shiftDistanceX, int shiftDistanceY) {
        int deltaX = getDeltaXRelativePlayer(direction), deltaY = getDeltaYRelativePlayer(direction);

        T gameObject = null;

        try {
            gameObject = classOfSetParameter.getDeclaredConstructor(int.class, int.class)
                    .newInstance(getX() + deltaX * shiftDistanceX, getY() + deltaY * shiftDistanceY);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return objectSet.contains(gameObject);
    }
}
