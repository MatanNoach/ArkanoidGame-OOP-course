//ID:316441534

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a collection of sprites.
 * Variables:
 * sprites - The collection
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * @return The list of sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Adds a sprite to the list.
     * @param s The sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * The function runs the timePassed function for every sprite.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * The function draws all sprites on the screen.
     * @param d The draw surface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
