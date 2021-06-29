//ID:316441534
package gui.animation;

import biuoop.DrawSurface;
import gui.gamedata.GameSettings;

import java.awt.*;

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
        d.setColor(Color.orange);
        d.fillRectangle(0,0,GameSettings.WINDOW_WIDTH,GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.white);
        d.drawText(GameSettings.HEADLINE_X, GameSettings.HEADLINE_Y,
                "paused -- press space to continue", GameSettings.FONT_SIZE * 2);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
