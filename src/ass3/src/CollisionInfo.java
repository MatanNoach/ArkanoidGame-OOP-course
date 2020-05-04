/**
 * The class represents information about a collision.
 * Variables:
 * collisionPoint - The point where the collision occurs
 * collisionObject - The object that causes the collision
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor.
     *
     * @param point The collision point
     * @param collisionObject The collision object
     */
    public CollisionInfo(Point point, Collidable collisionObject) {
        this.collisionPoint = new Point(point.getX(), point.getY());
        this.collisionObject = collisionObject;
    }

    /**
     * @return The collision object
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }

    /**
     * @return The collision point
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The function gets a point and returns to distance to the collision.
     *
     * @param p The point to check the distance from
     * @return The distance to the collision
     */
    public double getCollisionDistance(Point p) {
        return collisionPoint.distance(p);
    }
}
