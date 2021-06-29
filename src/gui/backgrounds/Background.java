//ID:316441534
package gui.backgrounds;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.gameobjects.Sprite;
import gui.shapes.Rectangle;

import java.awt.Image;

/**
 * The class represents a background in a rectangle shape in a level.
 * rectangle - The rectangle who represents the background
 */
public class Background implements Sprite {
    private Rectangle rectangle;

    /**
     * Constructor.
     *
     * @param rectangle The rectangle to copy from
     */
    public Background(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * sets an image for the background.
     *
     * @param img The image to set
     */
    public void setImage(Image img) {
        this.rectangle.setImage(img);
    }

    /**
     * @return The background's rectangle
     */
    public Rectangle getRectangle() {
        return this.rectangle;
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
    }
}
