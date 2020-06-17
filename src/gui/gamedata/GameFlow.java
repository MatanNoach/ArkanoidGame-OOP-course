//ID:316441534
package gui.gamedata;

import biuoop.KeyboardSensor;
import gui.Animation.*;
import gui.Levels.GameLevel;
import gui.Levels.LevelInformation;
import gui.gamelisteners.Counter;

import java.io.*;
import java.util.List;

/**
 * The class is responsible for creating a game flow.
 * variables:
 * ar - The animation runner
 * ks - The keyboard Sensor.
 * score - The score counter during the entire game.
 */
public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score;

    /**
     * Constructor.
     *
     * @param ar The animation runner.
     * @param ks The keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter();
    }

    /**
     * The function runs the main menu, and changes the UI by player's options.
     *
     * @param levels The levels of the game
     */
    public void runMenu(List<LevelInformation> levels) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(this.ks);
        menu.addSelection("h", "High Score", new ShowHiScoresTask(this.ar, ks, new HighScoreAnimation()));
        menu.addSelection("s", "Play", new GameFlowTask(this, levels));
        menu.addSelection("q", "Quit", new QuitTask());
        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * The function runs the levels in row.
     * <p>
     * if the player died, prints the Game Over screen.
     * if the player finished all the levels, prints the You Won screen.
     * </p>
     *
     * @param levels The list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        int winCounter = 0;
        //an animation that stop the run
        Animation stoppable;
        //for each level' run until it should stop
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, ks, ar, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            //if the stop was because the  player won, add 1 to the counter.
            if (level.isWin()) {
                winCounter++;
                // if he won in all levels, start Win Screen.
                if (winCounter == levels.size()) {
                    stoppable = new WinScreen(score.getValue());
                    ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, stoppable));
                    break;
                }
            }
            //if the stop was  because the player lost, start Game Over screen.
            if (level.isLost()) {
                stoppable = new GameOverScreen(score.getValue());
                ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, stoppable));
                break;
            }
        }
        //update the highest score if needed
        scoreUpdate();
        //reset the score
        this.score.decrease(this.score.getValue());
        //run the highScore screen
        ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, new HighScoreAnimation()));
    }

    public void scoreUpdate() {
        //read the current high score. if the new score is higher, update the file. else, do nothing.
        BufferedReader br = null;
        try {
            File file = new File("src\\ass7\\src\\highScores.txt");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            String[] str = line.split(" ");
            int topScore = Integer.parseInt(str[str.length - 1]);
            if (topScore < score.getValue()) {
                writeScore();
            }
        } catch (Exception e) {
            //if the file does not exist, create it.
            writeScore();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    System.out.println("Couldn't close the file");
                }
            }
        }
    }

    public void writeScore() {
        OutputStreamWriter os = null;
        try {
            File file = new File("src\\ass7\\src\\highScores.txt");
            os = new OutputStreamWriter(new FileOutputStream(file));
            os.write("The highest score so far is: " + this.score.getValue());
        } catch (Exception e) {
            System.out.println("Something went wrong while writing!");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
    }
}
