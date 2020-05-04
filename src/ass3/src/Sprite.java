//ID:316441534

import biuoop.DrawSurface;

/**
 * The interface represents a sprite object.
 */
public interface Sprite {
    /**
     * The function draws a sprite on the drawSurface.
     *
     * @param d The drawSurface to drawOn
     */
    void drawOn(DrawSurface d);

    /**
     * The function notifies a sprite that time has passed and activates a function.
     */
    void timePassed();

    /**
     * The function gets a game and add a sprite to it's collection.
     *
     * @param g The game to add
     */
    void addToGame(Game g);
}
