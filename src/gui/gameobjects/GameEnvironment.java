//ID: 316441534
package gui.gameobjects;

import gui.gamedata.GameSettings;
import gui.shapes.Line;
import gui.shapes.Point;
import gui.shapes.Rectangle;

import java.util.LinkedList;
import java.util.List;

/**
 * The class represents a collection of collidable items.
 * Variables:
 * collidables - the collection of collidable items
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    private GameSettings gameSettings;

    /**
     * Constructor that creates a new array list of collidable items.
     *
     * @param gameSettings The game settings
     */
    public GameEnvironment(GameSettings gameSettings) {
        this.collidables = new LinkedList<>();
        this.gameSettings = gameSettings;
    }

    /**
     * @return The game settings.
     */
    public GameSettings getGameSettings() {
        return this.gameSettings;
    }

    /**
     * if the object isn't null, add it to the list.
     *
     * @param c The object to add to the list.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            this.collidables.add(c);
        }
    }

    /**
     * Removes a collidable for the list
     *
     * @param c The collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * The function gets the trajectory, create a collision info object and returns it.
     *
     * @param trajectory The line to check the collision
     * @return A collisionInfo object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //a list of collidable objects in the trajectory course
        List<Collidable> inCourse = new LinkedList<>();
        //a list of closest points from each collidable object
        List<Point> closestPoints = new LinkedList<>();
        //finds all of the collidable objects in course and their closest points and adds them to the lists.
        for (Collidable c : this.collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                //Check if there are 2 rectangles sharing the same collision point
                int index = pointExists(p, closestPoints);
                inCourse.add(c);
                closestPoints.add(p);
                //if there are, remove the unwanted one
                if (index != -1) {
                    removeFromLists(collisionToRemove(c, inCourse.get(index), trajectory), inCourse, closestPoints);
                }
            }
        }
        //if there are none, return null
        if (inCourse.size() == 0) {
            return null;
        }
        //find the closest collision point
        Point closestPoint = trajectory.start().findClosestPoint(closestPoints);
        //return a new collision info object
        return new CollisionInfo(closestPoint, inCourse.get(closestPoints.indexOf(closestPoint)));
    }

    /**
     * The function gets a collidable object and removes it from the objects in course and remove it's collision point.
     *
     * @param c             The collidable object
     * @param inCourse      The list of collidables
     * @param closestPoints The list of points
     */
    private void removeFromLists(Collidable c, List<Collidable> inCourse, List<Point> closestPoints) {
        int index = inCourse.indexOf(c);
        inCourse.remove(c);
        closestPoints.remove(index);
    }

    /**
     * In case there are 2 collidables sharing the same point, return the unwanted one.
     *
     * @param c1         First collidable to check
     * @param c2         Second collidable to check
     * @param trajectory The trajectory line
     * @return The unwanted collidable
     */
    private Collidable collisionToRemove(Collidable c1, Collidable c2, Line trajectory) {
        Rectangle r1 = c1.getCollisionRectangle();
        Rectangle r2 = c2.getCollisionRectangle();
        double dx = trajectory.end().getX() - trajectory.start().getX();
        double dy = trajectory.end().getY() - trajectory.start().getY();
        //Find to closer rectangle in the ball's direction
        //If they are left to right
        if (r1.getUpperLeft().getY() == r2.getUpperLeft().getY()) {
            if (dx > 0) {
                if (r1.getUpperLeft().getX() > r2.getUpperLeft().getX()) {
                    return c1;
                }
            } else {
                if (r1.getUpperLeft().getX() < r2.getUpperLeft().getX()) {
                    return c1;
                }
            }
            return c2;
        }
        //If they are up and down
        if (dy > 0) {
            if (r1.getUpperLeft().getY() > r2.getUpperLeft().getY()) {
                return c1;
            }
        } else {
            if (r1.getUpperLeft().getY() < r2.getUpperLeft().getY()) {
                return c1;
            }
        }
        return c2;
    }

    /**
     * Check if the point is already exists in the arrayList.
     *
     * @param pointToCheck The point to check
     * @param points       The arrayList of points
     * @return the index of the point. -1 if it doesn't exist
     */
    private int pointExists(Point pointToCheck, List<Point> points) {
        for (Point p : points) {
            if (pointToCheck.equals(p)) {
                return points.indexOf(p);
            }
        }
        return -1;
    }
}
