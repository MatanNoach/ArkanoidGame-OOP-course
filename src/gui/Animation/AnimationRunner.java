//ID:316441534
package gui.Animation;


import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The class runs the game's animation.
 * variables:
 * gui - The game's gui.
 * framesPerSecond - how many frames per second the animation runs.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor.
     *
     * @param gui The animation GUI.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     * The function runs the game's animation.
     *
     * @param animation The animation object to run
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            //does one frame of animation
            animation.doOneFrame(d);
            this.gui.show(d);
            //calculates a delay time for a smooth movement on the screen
            long usedTime = System.currentTimeMillis() - startTime;
            long milleSecondLeftToSleep = millisecondPerFrame - usedTime;
            if (milleSecondLeftToSleep > 0) {
                sleeper.sleepFor(milleSecondLeftToSleep);
            }
        }
    }
}
