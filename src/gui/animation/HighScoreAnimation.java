//ID:316441534
package gui.animation;

import biuoop.DrawSurface;
import gui.gamedata.GameSettings;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * The class shows the highest score in the game.
 * variables:
 * stop - true of the class needs to stop it's animation and false otherwise
 */
public class HighScoreAnimation implements Animation {
    private boolean stop;

    /**
     * Constructor.
     */
    public HighScoreAnimation() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        int startX = 100, startY = 100;
        d.setColor(Color.ORANGE);
        d.fillRectangle(0, 0, GameSettings.WINDOW_WIDTH, GameSettings.WINDOW_HEIGHT);
        d.setColor(Color.white);
        //read the current high score. if there isn't a file, print "No High Score"
        BufferedReader br = null;
        try {
            File file = new File("src\\highScores.txt");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            d.drawText(GameSettings.HEADLINE_X, GameSettings.HEADLINE_Y, line, GameSettings.FONT_SIZE * 2);
        } catch (Exception e) {
            d.drawText(GameSettings.HEADLINE_X, GameSettings.HEADLINE_Y, "No High Score", GameSettings.FONT_SIZE * 2);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    System.out.println("(HighScoreAnimation.java) Couldn't close the file highScores.txt");
                }
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
