//ID:316441534
package gui.gamedata;

import biuoop.KeyboardSensor;
import gui.Animation.AnimationRunner;
import gui.Levels.GameLevel;
import gui.Levels.LevelInformation;
import gui.gamelisteners.Counter;

import java.util.List;

public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, ks, ar, this.score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
        }
    }

}
