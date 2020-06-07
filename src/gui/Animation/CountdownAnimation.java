//ID:316441534
package gui.Animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.gameobjects.SpriteCollection;

/**
 * The class is responsible for the count down before the level starts.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprites;
    private boolean start;

    /**
     * Constructor.
     *
     * @param numOfSeconds The num of seconds to wait before changing the text
     * @param countFrom    The num to count from
     * @param sprites      The sprites to initialize before starting the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection sprites) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.sprites = sprites;
        this.start = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        sprites.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, "The game starts in " + countFrom, 32);
        sleeper.sleepFor((long) (1000 * numOfSeconds));
        countFrom--;
        //TODO fix the delay problem here
        if (countFrom == -1) {
            this.start = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.start;
    }
}
