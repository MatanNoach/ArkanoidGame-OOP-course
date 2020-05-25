//ID: 316441534
package gui.gameobjects;

import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.shapes.Velocity;
import gui.shapes.Ball;

/**
 * The interface represents every collidable object. Each object should have these functions.
 */
public interface Collidable {
    /**
     * @return The function returns the gui.shapes.Rectangle of the gui.shapes.Rectangle representation of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The function checks what is the new velocity if an object hits a collidable object.
     *
     * @param collisionPoint  The collision point.
     * @param currentVelocity The current velocity of the moving object
     * @param hitter          The ball that hit the collidable object.
     * @return The new velocity of the moving object
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
