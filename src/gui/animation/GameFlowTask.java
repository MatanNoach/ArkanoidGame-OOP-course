//ID:316441534
package gui.animation;

import gui.levels.LevelInformation;
import gui.gamedata.GameFlow;

import java.util.List;

/**
 * The class runs the gameFlow task.
 * variables:
 * gameFlow - The gameFlow class
 * levels - The list of levels
 */
public class GameFlowTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     *
     * @param gameFlow The gameFlow to run
     * @param levels   The  levels to run
     */
    public GameFlowTask(GameFlow gameFlow, List<LevelInformation> levels) {
        this.gameFlow = gameFlow;
        this.levels = levels;
    }

    @Override
    public Void run() {
        this.gameFlow.runLevels(this.levels);
        return null;
    }
}
