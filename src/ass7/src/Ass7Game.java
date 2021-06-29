//ID:316441534

import biuoop.GUI;
import gui.animation.AnimationRunner;
import gui.factories.LevelSpecificationReader;
import gui.gamedata.GameSettings;
import gui.levels.LevelInformation;
import gui.gamedata.GameFlow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The class runs the the main functions of the game.
 */
public class Ass7Game {
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
        String levelsPath = GameSettings.LEVELS_PATH;
        if (args.length == 0) {
            loadLevel(levelsPath + "defaultLevel.txt");
        } else {
            loadLevel(levelsPath + args[0]);
        }
    }

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
     * The function loads a levelInformation list by a path to a definition file and runs them.
     *
     * @param path The path to the definition file
     */
    public static void loadLevel(String path) {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            LevelSpecificationReader levelReader = new LevelSpecificationReader();
            List<LevelInformation> levels = levelReader.fromReader(reader);
            runGame(levels);
        } catch (Exception e) {
            System.out.println("(Ass7Game.java) Something went wrong while reading the file " + path);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println("(Ass7Game.java) Couldn't close the file " + path);
            }
        }
    }
}
