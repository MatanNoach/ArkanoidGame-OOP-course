//ID:316441534
package gui.Animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import gui.gamedata.GameSettings;
import gui.gameobjects.SpriteCollection;

import java.awt.Color;

/**
 * The class is responsible for the count down before the level starts.
 * variables:
 * numOfSeconds - The number of seconds to wait before changing the text
 * countFrom - The number of seconds to start the counting from.
 * sprites - The sprites to present before the game starts
 * start - true if the game should start and false otherwise
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection sprites;
    private boolean start;
    private String levelName;

    /**
     * Constructor.
     *
     * @param numOfSeconds The num of seconds to wait before changing the text
     * @param countFrom    The num to count from
     * @param sprites      The sprites to initialize before starting the game.
     * @param levelName    The level's name
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection sprites, String levelName) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.sprites = sprites;
        this.start = false;
        this.levelName = levelName;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        //draw the the sprites for the player so he'll know what to prepare
        sprites.drawAllOn(d);
        //prints the level's name
        d.setColor(Color.BLACK);
        int nameWidth = GameSettings.WINDOW_WIDTH / 4;
        int nameHeight = GameSettings.FONT_SIZE;
        d.drawText(nameWidth, nameHeight, "Level: " + levelName, nameHeight);
        //draw the countdown text
        int screenWidth = GameSettings.WINDOW_WIDTH;
        int screenHeight = GameSettings.WINDOW_HEIGHT;
        int fontSize = 32;
        d.drawText(screenWidth / 2, screenHeight / 2, "The game starts in " + countFrom, fontSize);
        //hold the text
        sleeper.sleepFor((long) (1000 * numOfSeconds));
        //if the count reached an ending point, start the game
        if (countFrom == 0) {
            this.start = true;
        } else {
            //else lower the countdown number
            countFrom--;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.start;
    }
}
