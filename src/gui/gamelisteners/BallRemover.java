//ID:316441534
package gui.gamelisteners;

import gui.levels.GameLevel;
import gui.gameobjects.Block;
import gui.shapes.Ball;

/**
 * The class is responsible for removing balls from the game.
 * Variables:
 * game - The game
 * remainingBalls - The counter of the remaining balls in the game
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param gameLevel The game
     * @param remainingBalls The counter of the remaining balls in the game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
//        gameLevel.removeSprite(hitter);
//        this.remainingBalls.decrease(1);
    }
}
