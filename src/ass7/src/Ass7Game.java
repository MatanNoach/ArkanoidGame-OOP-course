import biuoop.GUI;
import gui.animation.AnimationRunner;
import gui.levels.*;
import gui.gamedata.GameFlow;

import java.util.ArrayList;
import java.util.List;

//ID:316441534
public class Ass7Game {
    /**
     * The function runs the game by a list of levels.
     *
     * @param levels A list of levels to run in a row.
     */
    public static void runGame(List<LevelInformation> levels) {
        GUI gui = new GUI("Game", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
        gameFlow.runMenu(levels);
        gui.close();
    }

    /**
     * The main function of the class.
     * <p>
     * if there are no user arguments, the function runs all the levels in a regular order.
     * if there are, the function runs the level by the user arguments values.
     * </p>
     *
     * @param args user arguments.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
//        levels.add(new Level1());
//        levels.add(new Level2());
        levels.add(new Level3());
        levels.add(new Level4());
        if (args.length == 0) {
            runGame(levels);
        }
        //if there are user arguments, for each one check if it's a level, and run the game by their order.
        List<LevelInformation> altLevels = new ArrayList<>();
        for (String arg : args) {
            try {
                int value = Integer.parseInt(arg);
                if (value <= levels.size() && value > 0) {
                    altLevels.add(levels.get(value - 1));
                }
            } catch (Exception e) {

            }
        }
        //if none of the user arguments are level numbers, run the game with the basic levels list
        if (altLevels.size() == 0) {
            runGame(levels);
        }
        runGame(altLevels);
    }
}
