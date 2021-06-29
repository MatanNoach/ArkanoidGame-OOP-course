//ID:316441534
package gui.animation;

import biuoop.DrawSurface;
import gui.gamedata.GameSettings;

import java.awt.*;

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
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.green);
        d.drawText(GameSettings.HEADLINE_X, GameSettings.HEADLINE_Y, "Game Over. Your score is " + score, GameSettings.FONT_SIZE * 2);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
