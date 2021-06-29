//ID:316441534
package gui.animation;

import biuoop.DrawSurface;
import gui.gamedata.GameSettings;

import java.awt.*;

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
        d.setColor(Color.yellow);
        d.fillRectangle(0,0,GameSettings.WINDOW_WIDTH,GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(GameSettings.HEADLINE_X, GameSettings.HEADLINE_Y,
                "You Win! Your score is " + score, GameSettings.FONT_SIZE * 2);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
