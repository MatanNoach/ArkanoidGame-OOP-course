//ID:316441534
package gui.Backgrounds;

import biuoop.DrawSurface;
import gui.Levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.Color;


public class Background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.lightGray);
        for (int i = 85; i <= 175; i += 7) {
            d.drawLine(i, 420, i - 30, GameSettings.WINDOW_HEIGHT);
        }
        d.fillCircle(100, 407, 20);
        d.fillCircle(125, 430, 22);
        d.setColor(Color.gray);
        d.fillCircle(140, 415, 27);
        d.fillCircle(155, 440, 20);
        d.fillCircle(170, 417, 25);
        d.setColor(Color.lightGray);
        for (int i = 480; i <= 575; i += 7) {
            d.drawLine(i, 300, i - 30, GameSettings.WINDOW_HEIGHT);
        }
        d.fillCircle(495, 290, 20);
        d.fillCircle(510, 310, 22);
        d.setColor(Color.gray);
        d.fillCircle(525, 295, 27);
        d.fillCircle(540, 320, 20);
        d.fillCircle(555, 300, 25);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
