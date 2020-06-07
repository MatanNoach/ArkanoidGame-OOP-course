//ID:316441534
package gui.Animation;


import biuoop.DrawSurface;

/**
 * The interface represents every class that can run animation.
 */
public interface Animation {
    /**
     * The function runs one frame of animation.
     *
     * @param d The draw surface to draw the frame on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The function checks if the animation should stop.
     *
     * @return True if it should stop, and false otherwise.
     */
    boolean shouldStop();
}
