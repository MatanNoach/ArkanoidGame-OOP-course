//ID:316441534
package gui.gameobjects;

import gui.shapes.Ball;
import gui.shapes.Velocity;
import gui.levels.GameLevel;
import biuoop.DrawSurface;
import gui.gamedata.GameSettings;
import biuoop.KeyboardSensor;
import gui.shapes.Point;
import gui.shapes.Rectangle;

/**
 * The class represent the paddle in the game which is also the player.
 * Variables:
 * rectangle - The paddle shape
 * keyboard - A keyboard sensor that reads the player's movement
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private KeyboardSensor keyboard;
    private int maxLeft;
    private int maxRight;
    private double speed;

    /**
     * Constructor.
     *
     * @param rectangle The rectangle
     * @param sensor    The keyboard sensor
     */
    public Paddle(Rectangle rectangle, KeyboardSensor sensor, double speed) {
        this.rectangle = new Rectangle(rectangle);
        this.keyboard = sensor;
        this.maxRight = GameSettings.WINDOW_WIDTH - GameSettings.BLOCK_EDGE_SIZE;
        this.maxLeft = GameSettings.BLOCK_EDGE_SIZE;
        this.speed = speed;
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double x = this.rectangle.getUpperLeft().getX();
        double rectangleWidth = this.rectangle.getWidth();
        //if the paddle hasn't reached the right border, change the location
        if (x + rectangleWidth < this.maxRight) {
            x += this.speed;
            //if the paddle reached the right border after the move, then stop it
            if (x + rectangleWidth > this.maxRight) {
                x = this.maxRight - rectangleWidth;
            }
        }
        //set the new location
        this.rectangle.setUpperLeft(x, this.rectangle.getUpperLeft().getY());
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveLeft() {
        double x = this.rectangle.getUpperLeft().getX();
        //if the paddle hasn't reached the left border
        if (x > this.maxLeft) {
            x -= speed;
            //if the paddle reached the left border after the move, then stop it
            if (x < maxLeft) {
                x = maxLeft;
            }
            //set the new location
            this.rectangle.setUpperLeft(x, this.rectangle.getUpperLeft().getY());
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //make sure the program doesn't crush if there is a collision on the bottom line
        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            //if the collision is on the top line
        } else if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()) {
            int hitSpot = findHitSpot(this.rectangle.getWidth(), collisionPoint.getX());
            int angle = -60;
            return currentVelocity.fromAngleAndSpeed(angle + hitSpot * 30, currentVelocity.getSpeed());
        }
        //make sure the program doesn't crush if there is a collision on the sides line
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The function gets a rectangle width and an x collision point and finds the hit spot.
     *
     * @param rectangleWidth The rectangle's width
     * @param collisionX     The collision X location
     * @return The hit spot
     */
    private int findHitSpot(double rectangleWidth, double collisionX) {
        double a = collisionX - this.rectangle.getUpperLeft().getX();
        double b = rectangleWidth / 5;
        int value = (int) (a / b);
        //edge fix
        if (value == 5) {
            value = 4;
        }
        return value;
    }
}
