//ID:316441534
package gui.backgrounds;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;

import java.awt.Color;

/**
 * The background for level 4.
 */
public class Background4 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //draw the background color
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        // draw rain for first cloud
        drawRain(85, 420, 175, GameSettings.WINDOW_HEIGHT, 45, 7, d);
        //draw first cloud
        drawCloud(100, 410, 20, d);
        //draw rain for second cloud
        drawRain(480, 300, 575, GameSettings.WINDOW_HEIGHT, 45, 7, d);
        //draw second cloud
        drawCloud(495, 290, 20, d);
    }

    /**
     * The function draw rain by parameters.
     *
     * @param xStart X to start the rain from
     * @param yStart y to start the rain from
     * @param xEnd   X to end the rain at
     * @param yEnd   y to end the rain at
     * @param angle  angle of the rain
     * @param space  space between each rain stream
     * @param d      Draw surface to draw the rain on
     */
    public void drawRain(int xStart, int yStart, int xEnd, int yEnd, int angle, int space, DrawSurface d) {
        d.setColor(Color.lightGray);
        for (int i = xStart; i <= xEnd; i += space) {
            d.drawLine(i, yStart, i - angle, yEnd);
        }
    }

    /**
     * The function draw a cloud.
     *
     * @param xStart X to start the cloud from
     * @param yStart y to start the cloud from
     * @param size   The size of the first cloud
     * @param d      Draw surface to draw the cloud on
     */
    public void drawCloud(int xStart, int yStart, int size, DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillCircle(xStart, yStart, size);
        d.fillCircle(xStart + 15, yStart + 20, size + 2);
        d.setColor(Color.gray);
        d.fillCircle(xStart + 20, yStart + 5, size + 7);
        d.fillCircle(xStart + 45, yStart + 30, size);
        d.fillCircle(xStart + 60, yStart + 10, size + 5);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
