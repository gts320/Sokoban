package controller;

import model.Box;
import model.Direction;
import model.GameObjects;
import model.Model;
import view.View;

public class Controller implements EventListener {
    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
        model.startLevel(model.getCurrentLevel());
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public Model getModel() {
        return model;
    }

    public static void main(String[] args) {
        new Controller();
    }

    @Override
    public void move(Direction direction) {
        model.movePlayer(direction);
        view.update();
    }

    @Override
    public void startLevel(int level) {
        model.startLevel(level);
        Box.setPushes(0);
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects() {
        return model.getGameObjects();
    }
}
