//ID:316441534
package gui.gamelisteners;

import gui.levels.GameLevel;
import gui.gameobjects.Block;
import gui.shapes.Ball;

/**
 * The class in charge for removing a block after it is being hit.
 * Variables:
 * game - The game
 * remainingBlock - The number of remaining blocks in the game
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param gameLevel The game
     * @param remainingBlocks The number of remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        gameLevel.removeCollidable(beingHit);
        gameLevel.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
    }
}
