//ID:316441534
package gui.gamelisteners;

import gui.gamedata.Game;
import gui.gameobjects.Block;
import gui.shapes.Ball;

/**
 * The class is responsible for removing balls from the game.
 * Variables:
 * game - The game
 * remainingBalls - The counter of the remaining balls in the game
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor.
     * @param game The game
     * @param remainingBalls The counter of the remaining balls in the game
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        this.remainingBalls.decrease(1);
    }
}
