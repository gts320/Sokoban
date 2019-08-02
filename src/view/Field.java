package view;

import controller.EventListener;
import model.Box;
import model.Direction;
import model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        addKeyListener(new KeyHandler());
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 580, 401);

        for (GameObject gameObject : view.getGameObjects().getAll()) gameObject.draw(g);

        g.setColor(Color.ORANGE);
        g.setFont(new Font("Default", Font.BOLD, 16));
        g.drawString("Level: " + view.getController().getModel().getCurrentLevel(), 10, 400);
        g.drawString("Moves: " + view.getGameObjects().getPlayer().getMoves(), 100, 400);
        g.drawString("Pushes: " + Box.getPushes(), 210, 400);

    }

    void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT) eventListener.move(Direction.LEFT);
            else if (keyCode == KeyEvent.VK_UP) eventListener.move(Direction.UP);
            else if (keyCode == KeyEvent.VK_RIGHT) eventListener.move(Direction.RIGHT);
            else if (keyCode == KeyEvent.VK_DOWN) eventListener.move(Direction.DOWN);
            else if (keyCode == KeyEvent.VK_R)
                eventListener.startLevel(view.getController().getModel().getCurrentLevel());
            else if (keyCode == KeyEvent.VK_Z) {
                view.getController().getModel().rollBack();
                view.update();
            }
        }
    }
}
