//ID:316441534
package gui.gamelisteners;

import gui.gamedata.Game;
import gui.gameobjects.Block;
import gui.shapes.Ball;

/**
 * The class in charge for removing a block after it is being hit.
 * Variables:
 * game - The game
 * remainingBlock - The number of remaining blocks in the game
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     * @param game The game
     * @param remainingBlocks The number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        game.removeCollidable(beingHit);
        game.removeSprite(beingHit);
        this.remainingBlocks.decrease(1);
    }
}
