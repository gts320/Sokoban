package model;

import java.awt.*;

public abstract class GameObject {
    private int x, y, width, height;

    GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        width = Model.FIELD_CELL_SIZE;
        height = Model.FIELD_CELL_SIZE;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    int getWidth() {
        return width;
    }

    void setWidth() {
        this.width = 3;
    }

    int getHeight() {
        return height;
    }

    void setHeight() {
        this.height = 3;
    }

    public abstract void draw(Graphics graphics);

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GameObject)) return false;

        GameObject gameObject = (GameObject) obj;

        if (x != gameObject.x) return false;
        if (y != gameObject.y) return false;
        if (width != gameObject.width) return false;
        return height == gameObject.height;
    }
}
