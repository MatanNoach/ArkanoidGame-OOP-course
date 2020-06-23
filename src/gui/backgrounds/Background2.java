//ID:316441534
package gui.backgrounds;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 2.
 */
public class Background2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        int sunSize = 40;
        int xSun = 100, ySun = 100;
        int linesSpaces = 16;
        int blocsHeight = 200;
        d.setColor(Color.ORANGE);
        d.fillCircle(xSun, ySun, sunSize + 10);
        d.setColor(Color.YELLOW);
        d.fillCircle(xSun, ySun, sunSize);
        //draw number line by the sun's size
        for (int i = 0; i < sunSize; i++) {
            d.drawLine(xSun + (i / 2), ySun, linesSpaces * i, blocsHeight);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
