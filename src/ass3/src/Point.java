//ID: 316441534

import java.util.List;

/**
 * The class represent a point as in a cartesian coordinate system.
 * Parameters:
 * x - The point's x value
 * y - The point's y value
 **/
public class Point {
    private double x;
    private double y;

    /**
     * Point's constructor.
     *
     * @param x Point's x value
     * @param y Point's y value
     **/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor.
     *
     * @param p The point to copy from
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Calculates the distance between two points.
     *
     * @param other The other point's location
     * @return The distance between this point and the other point
     **/
    public double distance(Point other) {
        //Find the distance between 2 x's and 2 y's
        double xDistance = this.x - other.getX();
        double yDistance = this.y - other.getY();
        //Raise their power by 2
        xDistance = Math.pow(xDistance, 2);
        yDistance = Math.pow(yDistance, 2);
        //Return the square root of their addition
        return Math.sqrt(xDistance + yDistance);
    }

    /**
     * @param other The other point's location
     * @return True if both points are equal, false otherwise
     **/
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }

    /**
     * @return The x value of this point
     **/
    public double getX() {
        return this.x;
    }

    /**
     * @return The x value of this point as integer
     **/
    public int getXInt() {
        return (int) this.x;
    }

    /**
     * Sets a new value for x.
     *
     * @param newX The new value for x
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * @return The y value of this point
     **/
    public double getY() {
        return this.y;
    }

    /**
     * @return The y value of this point as integer
     **/
    public int getYInt() {
        return (int) this.y;
    }

    /**
     * Sets a new value for x.
     *
     * @param newY The new value for y
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * @return String the Point's values
     */
    public String toString() {
        return ("X value: " + this.x + " Y value: " + this.y);
    }

    /**
     * The function get's a list of points, and returns the closest to this one.
     *
     * @param points A list of points
     * @return The closest point to this one. if the list is empty, return null.
     */
    public Point findClosestPoint(List<Point> points) {
        //if the list is empty, return null.
        if (points == null) {
            return null;
        }
        //sets initial value to the min distance, so I could compare to it
        double minDistance = this.distance(points.get(0));
        //index of the closest point
        int index = 0;
        double distance;
        //for loop that finds the shortest distance and saves the point's index
        for (int i = 1; i < points.size(); i++) {
            distance = this.distance(points.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }
        //return the point
        return points.get(index);
    }
}
