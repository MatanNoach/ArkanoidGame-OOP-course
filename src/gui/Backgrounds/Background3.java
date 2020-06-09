//ID:316441534
package gui.Backgrounds;

import biuoop.DrawSurface;
import gui.Levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 3.
 */
public class Background3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.darkGray);
        d.fillRectangle(50, 400, 125, 200);
        d.setColor(Color.white);
        d.fillRectangle(60, 410, 115, 180);
        for (int i = 1; i <= 5; i++) {
            d.setColor(Color.darkGray);
            d.fillRectangle(50 + i * 25, 400, 10, 200);
        }
        for (int i = 1; i <= 4; i++) {
            d.setColor(Color.darkGray);
            d.fillRectangle(50, 400 + i * 40, 125, 10);
        }
        d.setColor(Color.GRAY);
        d.fillRectangle(100, 330, 30, 70);
        d.fillRectangle(110, 150, 10, 180);
        d.setColor(Color.red);
        d.fillCircle(115, 140, 15);
        d.setColor(Color.WHITE);
        d.fillCircle(115, 140, 5);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
