//ID:316441534
package gui.Animation;

import biuoop.DrawSurface;

/**
 * The class represents the Game Over screen.
 * variables:
 * stop - true if the screen needs to stop and false otherwise
 * score - the players score before he lost
 */
public class GameOverScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * Constructor.
     *
     * @param score The ending score
     */
    public GameOverScreen(int score) {
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
