//ID:316441534
package gui.Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class represents an animation that stops by a key press.
 * Variables:
 * ks - The keyboard sensor
 * key - The key that stops the animation
 * animation - The running animation
 * stop - true if the screen needs to stop and false otherwise
 * isAlreadyPressed - true if the key was pressed before and false otherwise
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param ks The keyboard sensor
     * @param  key The key that stops the animation
     * @param animation The running animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor ks, String key, Animation animation) {
        this.ks = ks;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //run the animation
        this.animation.doOneFrame(d);
        //if the key is pressed now, and it wasn't pressed before, stop the animation
        if (this.ks.isPressed(key) && !isAlreadyPressed) {
            this.stop = true;
        } else if (!this.ks.isPressed(key)) { //if the key wasn't pressed, set isAlreadyPressed to false.
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Reset the stop back to false.
     */
    public void resetStop(){
        this.stop = false;
    }
}
