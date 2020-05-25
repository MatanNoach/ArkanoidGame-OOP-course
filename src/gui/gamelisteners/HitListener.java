//ID:316441534
package gui.gamelisteners;

import gui.gameobjects.Block;
import gui.shapes.Ball;

/**
 * The interface represents any object who needs to know when there is a hit.
 */
public interface HitListener {
    /**
     * The function makes the object do something when there is a hit event.
     *
     * @param beingHit The block that is being hit.
     * @param hitter   The ball who hit it.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
