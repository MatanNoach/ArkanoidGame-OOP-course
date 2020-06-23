//ID: 316441534
package gui.gameobjects;

import biuoop.DrawSurface;
import gui.gamelisteners.HitNotifier;
import gui.shapes.Ball;
import gui.shapes.Velocity;
import gui.levels.GameLevel;
import gui.shapes.Point;
import gui.shapes.Rectangle;
import gui.gamelisteners.HitListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a collidable block on the screen.it implements the collidable interface.
 * Variables:
 * rectangle - the block's shape and location
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<HitListener> hitListeners;

    /**
     * gui.gameObjects.Block's constructor.
     *
     * @param rectangle The rectangle on the screen that represents the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = new Rectangle(rectangle);
        this.hitListeners = new ArrayList<>();
    }

    public Block(Block b) {
        this.rectangle = new Rectangle(b.getCollisionRectangle());
        this.hitListeners = new ArrayList<>();
    }

    /**
     * The function notifies every HitListener object that this block was hit.
     *
     * @param hitter The hitter ball.
     */
    private void notifyAll(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //sets initial value to the new velocity
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();
        //If they share the same x or x+width, then its a hit on the left or right lines.
        //If the movement it is towards the line, then it is a hit and you can change dx.
        if ((collisionPoint.getX() == upperLeftX && currentVelocity.getDx() > 0)
                || (collisionPoint.getX() == upperLeftX + this.rectangle.getWidth() && currentVelocity.getDx() < 0)) {
            newVelocity.setDx(currentVelocity.getDx() * -1);
        }
        //If they share the same x or x+width, then its a hit on the top or bottom lines.
        //If the movement it is towards the line, then it is a hit and you can change dy.
        if ((collisionPoint.getY() == upperLeftY && currentVelocity.getDy() > 0)
                || (collisionPoint.getY() == upperLeftY + this.rectangle.getHeight() && currentVelocity.getDy() < 0)) {
            newVelocity.setDy(currentVelocity.getDy() * -1);
        }
        this.notifyAll(hitter);
        //Return the new velocity.
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
