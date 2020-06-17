//ID:316441534
package gui.Animation;

import gui.Levels.LevelInformation;
import gui.gamedata.GameFlow;

import java.util.List;

/**
 * The class runs the gameFlow task.
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
