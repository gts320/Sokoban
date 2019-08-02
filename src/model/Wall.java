package model;

import java.awt.*;

public class Wall extends GameObject implements Collisionable {

    Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GRAY);

        int x = getX(), y = getY(), upperLeftCornerX = x - Model.FIELD_CELL_SIZE / 2, upperLeftCornerY = y - Model.FIELD_CELL_SIZE / 2;

        graphics.fillRect(upperLeftCornerX, upperLeftCornerY, getWidth(), getHeight());
    }
}
