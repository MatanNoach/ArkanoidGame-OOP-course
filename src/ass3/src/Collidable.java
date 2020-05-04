//ID: 316441534
/**
 * The interface represents every collidable object. Each object should have these functions.
 */
public interface Collidable {
    /**
     * @return The function returns the Rectangle of the Rectangle representation of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The function checks what is the new velocity if an object hits a collidable object.
     *
     * @param collisionPoint  The collision point.
     * @param currentVelocity The current velocity of the moving object
     * @return The new velocity of the moving object
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}
