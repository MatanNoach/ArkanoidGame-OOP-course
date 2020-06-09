//ID:316441534
package gui.Backgrounds;

import biuoop.DrawSurface;
import gui.Levels.GameLevel;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 2.
 */
public class Background2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 40);
        for (int i = 0; i < 40; i++) {
            d.drawLine(100 + (i / 2), 100, 10 + 16 * i, 200);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
