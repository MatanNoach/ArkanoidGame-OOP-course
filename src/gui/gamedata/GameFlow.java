//ID:316441534
package gui.gamedata;

import biuoop.KeyboardSensor;
import gui.Animation.*;
import gui.Levels.GameLevel;
import gui.Levels.LevelInformation;
import gui.gamelisteners.Counter;

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
        Animation stoppable;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, ks, ar, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.isWin()) {
                winCounter++;
                if (winCounter == levels.size()) {
                    stoppable = new WinScreen(score.getValue());
                    ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, stoppable));
                    break;
                }
            }
            if (level.isLost()) {
                stoppable = new GameOverScreen(score.getValue());
                ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, stoppable));
                break;
            }
        }
    }
}
