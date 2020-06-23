//ID:316441534
package gui.backgrounds;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 1.
 */
public class Background1 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        int x = GameSettings.WINDOW_WIDTH / 2;
        int y = GameSettings.WINDOW_HEIGHT / 2;
        int lineLength = 100;
        int radius = 50;
        int radiusIncrease = 20;
        //draw background
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        //draw the cross
        d.setColor(Color.BLACK);
        d.drawLine(x - lineLength, y, x + lineLength, y);
        d.drawLine(x, y - lineLength, x, y + lineLength);
        //draw the 3 circles
        d.drawCircle(x, y, radius);
        d.drawCircle(x, y, radius + radiusIncrease);
        d.drawCircle(x, y, radius + radiusIncrease * 2);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
