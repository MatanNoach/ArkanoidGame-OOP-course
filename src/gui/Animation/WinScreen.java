//ID:316441534
package gui.Animation;

import biuoop.DrawSurface;

/**
 * The class represents the Win scree.
 * variables:
 * stop - true if the screen needs to stop and false otherwise
 * score - the players score in the end
 */
public class WinScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * Constructor.
     *
     * @param score The ending score
     */
    public WinScreen(int score) {
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
