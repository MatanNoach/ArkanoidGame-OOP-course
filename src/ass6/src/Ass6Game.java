//ID:316441534


import biuoop.GUI;
import gui.Animation.AnimationRunner;
import gui.Levels.*;
import gui.gamedata.GameFlow;

import java.util.ArrayList;
import java.util.List;

/**
 * The class runs the game.
 */
public class Ass6Game {
    /**
     * The main function of the class.
     *
     * @param args user arguments. Does not need any in the function
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new Level1());
        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        GUI gui = new GUI("Game", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
        gameFlow.runLevels(levels);
        gui.close();
    }
}
