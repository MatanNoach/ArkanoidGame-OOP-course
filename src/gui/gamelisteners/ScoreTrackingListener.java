//ID:316441534
package gui.gamelisteners;

import gui.gameobjects.Block;
import gui.shapes.Ball;

/**
 * The class keeps track of the player's score.
 * Variables:
 * currentScore - The player's current score
 * POINTS = The number of points the player ger for every block he hits
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    static final int POINTS = 5;

    /**
     * Constructor.
     *
     * @param scoreCounter The player's score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(POINTS);
    }
}
