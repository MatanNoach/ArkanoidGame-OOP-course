//ID:316441534
package gui.animation;

import biuoop.DrawSurface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * The class shows the highest score in the game.
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
        //read the current high score. if there isn't a file, print "No High Score"
        BufferedReader br = null;
        try {
            File file = new File("src\\ass7\\src\\highScores.txt");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            d.drawText(100, 100, line, 32);
        } catch (Exception e) {
            d.drawText(100, 100, "No High Score", 32);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    System.out.println("Failed to close the file!");
                }
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
