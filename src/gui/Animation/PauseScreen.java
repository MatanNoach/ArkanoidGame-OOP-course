//ID:316441534
package gui.Animation;

import biuoop.DrawSurface;

/**
 * The class is responsible for the pause screen.
 * variables:
 * stop - true if the screen needs to stop and false otherwise
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructor.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
