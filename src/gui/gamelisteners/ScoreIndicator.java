//ID:316441534
package gui.gamelisteners;

import biuoop.DrawSurface;
import gui.levels.GameLevel;
import gui.gamedata.GameSettings;
import gui.gameobjects.Sprite;
import gui.shapes.Rectangle;

import java.awt.Color;

/**
 * The class represents a score indicator for the game.
 * Variables:
 * scoreCounter - The score counter
 * Block - The block where the score is printed
 * FONT_SIZE - The font size
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle rectangle;

    /**
     * Constructor.
     *
     * @param scoreCounter The score counter
     * @param block        The block where the score is printed
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle block) {
        this.scoreCounter = scoreCounter;
        this.rectangle = block;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draws the block of the score text
        int width = (int) this.rectangle.getWidth();
        int x = (int) this.rectangle.getUpperLeft().getX() + width / 2;
        int y = (int) this.rectangle.getUpperLeft().getY() + GameSettings.FONT_SIZE;
        this.rectangle.drawOn(d);
        //prints the text
        d.setColor(Color.BLACK);
        d.drawText(x, y, "Score: " + scoreCounter.getValue(), GameSettings.FONT_SIZE);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
