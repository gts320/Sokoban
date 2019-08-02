package model;

import java.awt.*;

public class Box extends GameObject implements Movable, Collisionable {
    private static int pushes;

    Box(int x, int y) {
        super(x, y);
    }

    static public int getPushes() {
        return pushes;
    }

    public static void setPushes(int pushes) {
        Box.pushes = pushes;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);

        int x = getX(),
                y = getY(),
                upperLeftCornerX = x - Model.FIELD_CELL_SIZE / 2,
                upperLeftCornerY = y - Model.FIELD_CELL_SIZE / 2,
                deltaX = upperLeftCornerX + Model.FIELD_CELL_SIZE,
                deltaY = upperLeftCornerY + Model.FIELD_CELL_SIZE;

        graphics.drawRect(upperLeftCornerX, upperLeftCornerY, getWidth(), getHeight());
        graphics.drawLine(upperLeftCornerX, upperLeftCornerY, deltaX, deltaY);
        graphics.drawLine(deltaX, upperLeftCornerY, upperLeftCornerX, deltaY);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
        pushes++;
    }
}
