//ID:316441534
package gui.Backgrounds;

import biuoop.DrawSurface;
import gui.Levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 1.
 */
public class Background1 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawLine(300, 200, 500, 200);
        d.drawLine(400, 100, 400, 300);
        d.drawCircle(400, 200, 50);
        d.drawCircle(400, 200, 70);
        d.drawCircle(400, 200, 90);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
