//ID: 316441534
package gui.shapes;

import biuoop.DrawSurface;
import gui.gameobjects.Sprite;
import gui.gameobjects.GameEnvironment;
import gui.gameobjects.CollisionInfo;
import gui.gamedata.Game;
import gui.gamedata.GameSettings;
import java.awt.Color;

/**
 * The class represents a gui.shapes.Ball in a cartesian coordinate system.
 * Parameters:
 * center - The point center point of the ball
 * radius - The radius of the ball
 * color - The color of the ball
 * gui.shapes.Velocity - The change in the ball's position
 * gameEnvironment - All of the objects the ball can hit
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor.
     *
     * @param center          The point center point of the ball
     * @param r               The radius of the ball
     * @param color           The color of the ball
     * @param gameEnvironment The gameEnvironment
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(center);
        this.radius = r;
        this.color = color;
        //Set the velocity to zero movement
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @return The x value of the ball's center
     */
    public int getX() {
        return this.center.getXInt();
    }

    /**
     * @return The y value of the ball's center
     */
    public int getY() {
        return this.center.getYInt();
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * @return The ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the velocity value.
     *
     * @param v The velocity value to set
     */
    public void setVelocity(Velocity v) {
        this.velocity.setDy(v.getDy());
        this.velocity.setDx(v.getDx());
    }

    /**
     * The function find the trajectory of the ball by creating a line for the starting point to the end of the window.
     *
     * @param maxHeight The window's max height
     * @param maxWidth  The window's max width
     * @return The trajectory line
     */
    private Line findTrajectory(int maxHeight, int maxWidth) {
        // copy the center of the ball as starting point
        Point start = new Point(this.center);
        //while the point is still in the window's borders, apply the velocity
        while ((start.getX() > 0 && start.getX() < maxWidth) && (start.getY() > 0 && start.getY() < maxHeight)) {
            start = this.velocity.applyToPoint(start);
        }
        //saves the ending point
        Point end = new Point(start);
        //return a new line from the center to the end of the movement
        return new Line(new Point(this.center), end);
    }

    /**
     * The function applies the velocity to the center of the ball.
     * <p>
     * if the ball reached an object, change the velocity and inform the collide object with hit() function.
     * </p>
     *
     * @param collision The collision info of the ball's trajectory
     * @return the new center location.
     */
    private Point applyToCenter(CollisionInfo collision) {
        //the center's x and y after the movement
        double afterMoveX = this.center.getX() + this.velocity.getDx();
        double afterMoveY = this.center.getY() + this.velocity.getDy();
        //set the ending point as the collision point
        Point end = collision.getCollisionPoint();
        boolean edge = false;
        //check the movement in x axis and y axis. if the ball has passed the ending point. means it reached an edge.
        //set the center of the ball *close* to the collision point.
        if (this.velocity.getDx() > 0) {
            if (afterMoveX + this.radius >= end.getX()) {
                this.center.setX(end.getX() - this.radius);
                edge = true;
            }
        } else if (this.velocity.getDx() < 0) {
            if (afterMoveX - this.radius <= end.getX()) {
                this.center.setX(end.getX() + this.radius);
                edge = true;
            }
        }
        if (this.velocity.getDy() > 0) {
            if (afterMoveY + this.radius >= end.getY()) {
                this.center.setY(end.getY() - this.radius);
                edge = true;
            }
        } else if (this.velocity.getDy() < 0) {
            if (afterMoveY - this.radius <= end.getY()) {
                this.center.setY(end.getY() + this.radius);
                edge = true;
            }
        }
        //if the ball has reached an edge, inform the object with hit() function, and change the velocity
        //to the one returned from it.
        if (edge) {
            Velocity newVelocity = collision.getCollisionObject().hit(this, collision.getCollisionPoint(), this.velocity);
            setVelocity(newVelocity);
        }
        //apply the velocity change to the ball's center
        this.center = this.velocity.applyToPoint(this.center);
        //return the velocity
        return this.center;
    }

    /**
     * Make a change in the ball movement by the velocity value.
     */
    public void moveOneStep() {
        int maxHeight = GameSettings.WINDOW_HEIGHT;
        int maxWidth = GameSettings.WINDOW_WIDTH;
        //make the move by changing the center of the ball
        this.center = applyToCenter(this.gameEnvironment.getClosestCollision(findTrajectory(maxHeight, maxWidth)));
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
