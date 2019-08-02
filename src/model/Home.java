package model;

import java.awt.*;

public class Home extends GameObject {
    Home(int x, int y) {
        super(x, y);
        setWidth();
        setHeight();
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
