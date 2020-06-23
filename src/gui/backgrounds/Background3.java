//ID:316441534
package gui.backgrounds;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 3.
 */
public class Background3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        int bHeight = 200, bWidth = 125, whiteShift = 10;
        int leftShift = 50, upShift = GameSettings.WINDOW_HEIGHT - bHeight;
        //draw the background color
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        //draw the building dark base
        d.setColor(Color.darkGray);
        d.fillRectangle(leftShift, upShift, bWidth, bHeight);
        //draw the building white base
        d.setColor(Color.white);
        d.fillRectangle(leftShift + whiteShift, upShift + whiteShift, bWidth - whiteShift, bHeight - whiteShift);
        //draw vertical lines
        int vSpacing = 25;
        int linesWidth = 10;
        for (int i = 1; i <= 5; i++) {
            d.setColor(Color.darkGray);
            d.fillRectangle(leftShift + i * vSpacing, upShift, linesWidth, bHeight);
        }
        //draw horizontal lines
        int hSpacing = 40;
        for (int i = 1; i <= 4; i++) {
            d.setColor(Color.darkGray);
            d.fillRectangle(leftShift, upShift + i * hSpacing, bWidth, linesWidth);
        }
        //draw pole
        int baseHeight = 70, baseWidth = 30;
        int poleHeight = 150;
        d.setColor(Color.GRAY);
        d.fillRectangle(leftShift * 2, upShift - baseHeight, baseWidth, baseHeight);
        d.fillRectangle(leftShift * 2 + whiteShift, upShift - baseHeight - poleHeight, baseWidth / 3, poleHeight);
        //draw the signal
        int smallRadius = 5, bigRadius = 15;
        int yCenter = upShift - baseHeight - poleHeight - smallRadius;
        int xCenter = leftShift * 2 + bigRadius;
        d.setColor(Color.red);
        d.fillCircle(xCenter, yCenter, bigRadius);
        d.setColor(Color.WHITE);
        d.fillCircle(xCenter, yCenter, smallRadius);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
