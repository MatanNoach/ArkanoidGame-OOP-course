//ID: 316441534

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * Parameters:
 * dx - The change in x axis
 * dy - The change in y axis
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx The change in x value
     * @param dy The change in y value
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The function gets a point and return a new point with position (x+dx, y+dy).
     *
     * @param p A point with starting position
     * @return A new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * @return The dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @param newDx The new dx to set
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @return The dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param newDy The new dy to set
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * A constructor for velocity that convert angle and speed to dx and dy.
     *
     * @param angle The angle of the movement
     * @param speed The speed of the movement
     * @return A velocity with dx and dy values
     */
    public Velocity fromAngleAndSpeed(double angle, double speed) {
        double newDx, newDy;
        newDx = Math.sin(Math.toRadians(angle)) * speed;
        newDy = Math.cos(Math.toRadians(angle)) * speed;
        //-newDy because the axis is reversed
        return new Velocity(newDx, -newDy);
    }

    /**
     * @return Convert the velocity to speed and returns it.
     */
    public double getSpeed() {
        double dxSquared = Math.pow(dx, 2);
        double dySquared = Math.pow(dy, 2);
        return Math.sqrt(dxSquared + dySquared);
    }

    /**
     * The function compares this velocity values with another velocity object.
     *
     * @param velocity The other velocity to compare with
     * @return True if they are equal and false otherwise
     */
    public boolean equals(Velocity velocity) {
        return (this.dx == velocity.getDx() && this.dy == velocity.getDy());
    }
}
