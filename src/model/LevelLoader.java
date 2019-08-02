package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class LevelLoader {

    private Path levels;

    LevelLoader(Path levels) {
        this.levels = levels;
    }

    GameObjects getLevel(int level) {
        if (level % 60 == 0) level = 60;
        else level %= 60;

        List<Wall> walls = new ArrayList<>();
        List<Box> boxes = new ArrayList<>();
        List<Home> homes = new ArrayList<>();

        Player player = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(levels.toString()))) {

            for (String s; (s = bufferedReader.readLine()) != null; ) {
                if (s.equals("Maze: " + level)) {
                    while (!(bufferedReader.readLine()).equals("")) ;

                    int y = 0;
                    int halfFieldCellSize = Model.FIELD_CELL_SIZE / 2;

                    while (!(s = bufferedReader.readLine()).equals("")) {

                        for (int x = 0; x < s.length(); x++) {
                            char c = s.charAt(x);
                            int xForAllGameObject = x * Model.FIELD_CELL_SIZE + halfFieldCellSize,
                                    yForAllGameObject = y * Model.FIELD_CELL_SIZE + halfFieldCellSize;

                            if (c != ' ') {
                                if (c == 'X')
                                    walls.add(new Wall(xForAllGameObject, yForAllGameObject));
                                else if (c == '*')
                                    boxes.add(new Box(xForAllGameObject, yForAllGameObject));
                                else if (c == '.')
                                    homes.add(new Home(xForAllGameObject, yForAllGameObject));
                                else if (c == '&') {
                                    boxes.add(new Box(xForAllGameObject, yForAllGameObject));
                                    homes.add(new Home(xForAllGameObject, yForAllGameObject));
                                } else if (c == '@')
                                    player = new Player(xForAllGameObject, yForAllGameObject);
                            }
                        }
                        y++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}

