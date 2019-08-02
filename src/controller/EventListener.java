package controller;

import model.Direction;

public interface EventListener {
    void move(Direction direction);

    void levelCompleted(int level);

    void startLevel(int level);
}
